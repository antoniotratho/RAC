package br.com.fabricante.addon.exemplos.jobs;

import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.util.BaseSPBean;

import javax.ejb.SessionBean;
/*

        É obrigatório seguir o padrão abaixo para criar Jobs (Rotinas automatizadas executadas de tempos em tempos):

        A documentação É OBRIGATÓRIA!!!!!! Sem ela, o xdoclet não conseguirá gerar suas interfaces e o serviço nunca será encontrado.

        1 - Nome da classe sempre termina com *SPBean.
        2 - @ejb.bean - Sempre será o Nome da classe, mas termina somente com *SP.
        3 - jndi-name - Sempre será o caminho completo para a classe, cujo nome seguirá o padrão acima, e sempre será separado por "/".
        4 - type - Recomendado é stateless, caso deseje outro, consulte documentação.
        5 - transaction-type - Recomendado é "Container". Caso deseje outro, consulte documentação.
        6 - view-type - Sempre será "local".
        7 - @ejb.transaction - Recomendado é "Supports". Caso deseje outro, consulte documentação.
        8 - @ejb.util - Recomendado é false. Caso deseje outro, consulte documentação.


        IMPORTANTE: É necessário também editar o arquivo 'mgeschedule.xml'. Este arquivo está em model/resources/META-INF/mgeschedule.xml.
        Todo serviço novo jbo deve ser declarado neste arquivo, caso contrário, este não será executado.

* */


/**
 * @ejb.bean name="ExemploJobSP"
 * jndi-name="br/com/fabricante/addon/exemplos/jobs/ExemploJobSP"
 * type="Stateless"
 * transaction-type="Container"
 * view-type="local"
 * @ejb.transaction type="Supports"
 * @ejb.util generate="false"
 */
public class ExemploJobSPBean extends BaseSPBean implements SessionBean {

    /*
        É OBRIGATÓRIO criar o método getScheduleConfig. Este método sempre irá retornar uma String contendo o intervalo das execuções.

        O intervalo deverá ser retornado em milisegundos. Ex: 1 segundo são 1000 milisegundos. 1 hora são 3600000 milisegundos.

        A documentação É OBRIGATÓRIA!!!

        1 - @ejb.interface-method - Sempre será "local".
    * */

    /**
     * @ejb.interface-method view-type = "local"
     */
    public String getScheduleConfig() throws java.lang.Exception {
        return "&1000";
    }


      /*
        É OBRIGATÓRIO criar o método onSchedule. Este é o método onde a rotina será executada.

        A documentação É OBRIGATÓRIA!!!

        1 - @ejb.interface-method
        2 - @ejb.transaction - Recomendado é "Supports". Caso deseje outro, consulte documentação.
    * */

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Supports"
     */
    public void onSchedule() throws Exception, MGEModelException {
        System.out.println("Job Local foi chamado.");
    }
}
