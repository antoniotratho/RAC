package br.com.tratho.rac;

import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.util.FinderWrapper;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.BaseSPBean;
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.ws.ServiceContext;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sankhya.util.JdbcUtils;
import com.sankhya.util.XMLUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;

import javax.ejb.SessionBean;

/**
 * @ejb.bean name="ListarRACSP"
 *           jndi-name="br/com/tratho/rac/ListarRACSP"
 *           type="Stateless"
 *           transaction-type="Container"
 *           view-type="remote"
 * @ejb.transaction type="Supports"
 * @ejb.util generate="false"
 */
public class ListarRACSPBean extends BaseSPBean implements SessionBean {

    /**
     * @throws MGEModelException
     * @ejb.interface-method view-type="remote"
     */
    public void listar(ServiceContext ctx) throws MGEModelException {
        JdbcWrapper jdbc = null;
        NativeSql sql = null;
        ResultSet rset = null;
        SessionHandle hnd = null;

        try {
            hnd = JapeSession.open();
            hnd.setFindersMaxRows(-1);
            EntityFacade entity = EntityFacadeFactory.getDWFFacade();
            jdbc = entity.getJdbcWrapper();
            jdbc.openSession();

            sql = new NativeSql(jdbc);
            sql.appendSql("SELECT NUATEND, CODPARC, DTABERTURA, CODUSU, NUMNOTA, OCORRENCIA, " +
                          "ANALISECAUSA, ACAOIMEDIATA, TIPOOCORRENCIA, CATEGORIA, OBSQUALIDADE, " +
                          "APROVACAOGERENCIACOMERCIAL, DTHORAAPROVACAOCOMERCIAL, JUSTIFICATIVACOMERCIAL, " +
                          "APROVACAOGERENCIAFINANCEIRO, DTHORAAPROVACAOFINANCEIRO, JUSTIFICATIVAFINANCEIRO, " +
                          "CODUSUAPROVADORCOMERCIAL, CODUSUAPROVADORFINANCEIRO, TODOSDOCUMENTOS " +
                          "FROM REGISTROATENDCLIENTE");
            rset = sql.executeQuery();

            // Create a JSON array to hold all rows
            JsonArray jsonArray = new JsonArray();

            // Iterate through the ResultSet and build JSON objects for each row
            while (rset.next()) {
                JsonObject row = new JsonObject();
                row.addProperty("NUATEND", rset.getInt("NUATEND"));
                row.addProperty("CODPARC", rset.getInt("CODPARC"));
                row.addProperty("DTABERTURA", rset.getTimestamp("DTABERTURA") != null ? rset.getTimestamp("DTABERTURA").toString() : null);
                row.addProperty("CODUSU", rset.getInt("CODUSU"));
                row.addProperty("NUMNOTA", rset.getInt("NUMNOTA"));
                row.addProperty("OCORRENCIA", rset.getString("OCORRENCIA"));
                row.addProperty("ANALISECAUSA", rset.getString("ANALISECAUSA"));
                row.addProperty("ACAOIMEDIATA", rset.getString("ACAOIMEDIATA"));
                row.addProperty("TIPOOCORRENCIA", rset.getInt("TIPOOCORRENCIA"));
                row.addProperty("CATEGORIA", rset.getString("CATEGORIA"));
                row.addProperty("OBSQUALIDADE", rset.getString("OBSQUALIDADE"));
                row.addProperty("APROVACAOGERENCIACOMERCIAL", rset.getString("APROVACAOGERENCIACOMERCIAL"));
                row.addProperty("DTHORAAPROVACAOCOMERCIAL", rset.getTimestamp("DTHORAAPROVACAOCOMERCIAL") != null ? rset.getTimestamp("DTHORAAPROVACAOCOMERCIAL").toString() : null);
                row.addProperty("JUSTIFICATIVACOMERCIAL", rset.getString("JUSTIFICATIVACOMERCIAL"));
                row.addProperty("APROVACAOGERENCIAFINANCEIRO", rset.getString("APROVACAOGERENCIAFINANCEIRO"));
                row.addProperty("DTHORAAPROVACAOFINANCEIRO", rset.getTimestamp("DTHORAAPROVACAOFINANCEIRO") != null ? rset.getTimestamp("DTHORAAPROVACAOFINANCEIRO").toString() : null);
                row.addProperty("JUSTIFICATIVAFINANCEIRO", rset.getString("JUSTIFICATIVAFINANCEIRO"));
                row.addProperty("CODUSUAPROVADORCOMERCIAL", rset.getInt("CODUSUAPROVADORCOMERCIAL"));
                row.addProperty("CODUSUAPROVADORFINANCEIRO", rset.getInt("CODUSUAPROVADORFINANCEIRO"));
                row.addProperty("TODOSDOCUMENTOS", rset.getString("TODOSDOCUMENTOS"));
                jsonArray.add(row);
            }

            // Create the JSON response object
            JsonObject response = new JsonObject();
            response.addProperty("status", "success");
            response.add("atendimentos", jsonArray);
            ctx.setJsonResponse(response);

        } catch (Exception e) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("status", "error");
            errorResponse.addProperty("message", e.getMessage());
            ctx.setJsonResponse(errorResponse);
            MGEModelException.throwMe(e);
        } finally {
            JdbcUtils.closeResultSet(rset);
            NativeSql.releaseResources(sql);
            JdbcWrapper.closeSession(jdbc);
            JapeSession.close(hnd);
        }
    }
}