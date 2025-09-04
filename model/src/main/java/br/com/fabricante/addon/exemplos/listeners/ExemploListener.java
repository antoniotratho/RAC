package br.com.fabricante.addon.exemplos.listeners;

import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.PersistenceEventAdapter;

/*
  Este exemplo extende de PersitenceEventAdapter.

  Para que este listener seja funcional, � OBRIGAT�RIO criar o arquivo 'extension-listeners.xml', que fica no caminho model/resources/META-INF/extension-listeners.xml

* */
public class ExemploListener extends PersistenceEventAdapter {

    /*
        Sobrescrever os eventos desejados e implementar sua funcionalidade.
        Em caso de d�vidas, consulte certifica��o de desenvolvimento de extens�es.
    * */
    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {
        super.beforeInsert(event);
    }
}
