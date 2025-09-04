package br.com.fabricante.addon.exemplos.servicos;

import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.BaseSPBean;
import br.com.sankhya.ws.ServiceContext;
import com.google.gson.JsonObject;

import javax.ejb.SessionBean;
/*

        É obrigatório seguir o padrão abaixo para criar Serviços (similares à endpoints):

        A documentação é OBRIGATÓRIA!!!!!! Sem ela, o xdoclet não conseguirá gerar suas interfaces e o serviço nunca será encontrado.

        1 - Nome da classe sempre termina com *SPBean.
        2 - @ejb.bean sempre será o Nome da classe, mas termina somente com *SP.
        3 - jndi-name sempre será o caminho completo para a classe, cujo nome seguirá o padrão acima, e sempre será separado por "/".
        4 - type - Recomendado é stateless, caso deseje outro, consulte documentação.
        5 - transaction-type - Recomendado é "Container". Caso deseje outro, consulpe documentação.
        6 - view-type - Sempre será "remote".
        7 - @ejb.transaction - Recomendado é "Supports". Caso deseje outro, consulte documentação.
        8 - @ejb.util - Recomendado é false. Caso deseje outro, consulte documentação.

        IMPORTANTE: É necessário também editar o arquivo 'service-providers.xml'. Este arquivo está em vc/src/main/WEB-INF/resources/service-providers.xml.
        Todo serviço novo deve ser declarado neste arquivo, caso contrário, este não será localizado em tempo de execução.
 */


/**
 * @ejb.bean name="ExemploServicoSP"
 * jndi-name="br/com/fabricante/addon/exemplos/servicos/ExemploServicoSP"
 * type="Stateless"
 * transaction-type="Container"
 * view-type="remote"
 * @ejb.transaction type="Supports"
 * @ejb.util generate="false"
 */
public class ExemploServicoSPBean extends BaseSPBean implements SessionBean {

    /*
            A criação de um método que será chamado (equivalente a um endpoint) deve seguir o padrão abaixo.

            A documentação é OBRIGATÓRIA!!! Sem ela, o xdoclet não conseguirá gerar suas interfaces e este método não será encontrado.

            1 - @ejb.interface-method - Sempre será "remote".
            2 - O parâmetro br.com.sankhya.ws.ServiceContext é OBRIGATÓRIO. Esta é a assinatura padrão do método. Ex: public void meuMetodo(br.com.sankhya.ws.ServiceContext ctx) {...}

            Importante: O método abaixo será chamado com URL/nome_projeto/service.sbr?serviceName=ExemploServicoSP.getAlgumaInfo, onde
            nome_projeto é o valor de 'rootProject.name' que está no arquivo 'settings.gradle'.

    */

    /**
     * @throws MGEModelException
     * @ejb.interface-method tview-type="remote"
     */
    public void getAlgumaInfo(ServiceContext ctx) throws MGEModelException {
        JsonObject object = new JsonObject();
        object.addProperty("codparc", "123456");
        ctx.setJsonResponse(object);
    }
}


