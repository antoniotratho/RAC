package br.com.fabricante.addon.exemplos.listeners;

import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.PersistenceEventAdapter;

/*
  Este exemplo extende de PersitenceEventAdapter.

  Para que este listener seja funcional, é OBRIGATÓRIO criar o arquivo 'extension-listeners.xml', que fica no caminho model/resources/META-INF/extension-listeners.xml

* */
public class ExemploListener extends PersistenceEventAdapter {

    /*
        Sobrescrever os eventos desejados e implementar sua funcionalidade.
        Em caso de dúvidas, consulte certificação de desenvolvimento de extensões.
    * */
    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {
        super.beforeInsert(event);
    }
}
