package org.identidad.matriculas.process;

import okhttp3.OkHttpClient;
import org.identidad.matriculas.Configuration;
import org.identidad.matriculas.LogTracker;
import org.identidad.matriculas.data.Store;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GestorDeConsultas extends Thread  {

    private final int  tramiteInicial;
    private final int  tramiteFinal;
    private final Date fecha;

    private final Executor     executor;
    private final OkHttpClient httpClient;
    private final Store store;
    private final LogTracker logTracker;

    private int tramitesEnProceso;

    public GestorDeConsultas(int tramiteInicial, int tramiteFinal, Date fecha, LogTracker logTracker, Configuration configuration, Store store) {
        this.tramiteInicial = tramiteInicial;
        this.tramiteFinal   = tramiteFinal;
        this.fecha          = fecha;
        this.executor = Executors.newFixedThreadPool(5);
        this.store = store;
        this.logTracker = logTracker;

        if (configuration.isProxyConfigured()) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(configuration.getProxyHost(), configuration.getProxyPort()));
            this.httpClient = new OkHttpClient.Builder().proxy(proxy).build();
        } else {
            this.httpClient = new OkHttpClient();
        }

    }

    public void run() {
        for(int tramite = tramiteInicial; tramite <= tramiteFinal; tramite++) {
            executor.execute(
                    new ConsultaDeTramite(
                            tramite,
                            fecha,
                            httpClient,
                            store,
                            this));
            updateTramitesEnProceso(+1);
        }
    }

    public void processFinished(String message) {
        logTracker.addToLog(message);
        updateTramitesEnProceso(-1);
    }

    private synchronized void updateTramitesEnProceso(int diff) {
        tramitesEnProceso += diff;
        if (tramitesEnProceso==0) {
            logTracker.addToLog("Proceso Finalizado.");
        }
    }
}
