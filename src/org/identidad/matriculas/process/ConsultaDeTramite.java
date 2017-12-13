package org.identidad.matriculas.process;

import okhttp3.OkHttpClient;
import org.identidad.matriculas.data.Store;
import org.identidad.matriculas.utils.Rutinas;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ConsultaDeTramite implements Runnable  {

    private static final String[] LINES_TO_IGNORE = new String[] {
            "https://informes.dnrpi.jus.gob.ar/sipel/SolicitudDeinforme/ObtencionDeTramite",
            "Avda. Belgrano 1130",
            "4383-9468 / 4381-4771"
    };

    private final OkHttpClient okHttpClient;
    private final int numeroDeTramite;
    private final Date fecha;
    private final Store store;
    private final GestorDeConsultas gestorDeConsultas;


    public ConsultaDeTramite(int numeroDeTramite, Date fecha, OkHttpClient okHttpClient, Store store, GestorDeConsultas gestorDeConsultas) {
        this.numeroDeTramite = numeroDeTramite;
        this.fecha = fecha;
        this.okHttpClient = okHttpClient;
        this.store = store;
        this.gestorDeConsultas = gestorDeConsultas;
    }

    public void run() {
        try {
            String page = Rutinas.getPage(okHttpClient, numeroDeTramite, fecha);
            processPage(page);
        } catch(Exception e) {
            System.err.printf("Error al consultar el trámite %d: %s\n", numeroDeTramite, e.getMessage());
            e.printStackTrace();
            gestorDeConsultas.processFinished(String.format("ERROR. Trámite: %d. Detalle: %s", numeroDeTramite, e.getMessage()));
        }
    }

    private void processPage(String page) {
        boolean markerLineFound = false;
	    try(Scanner scanner = new Scanner(page)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!ignorarLinea(line)) {
                    markerLineFound = isMarkerLine(markerLineFound, line, numeroDeTramite);
                    if (markerLineFound) {

                        // No hay información
                        if(extract(line,"Por favor, verifique los datos e intente nuevamente") != null ){
                            break;
                        }

                        // Ya emitido
                        if (extract(line,"retirar su") != null) {
                            break;
                        }

                        // La matricula existe
                        String matricula = extract(line,"[0-9]{1,2}-[0-9]{1,5}([/,0-9,\\-]{1,5})");
                        if (matricula!=null) {
                            matricula = Rutinas.sacoCeros(matricula);
                            store.add(matricula, fecha);
                            gestorDeConsultas.processFinished(String.format("Tramite: %d. Matricula: %s", numeroDeTramite, matricula));
                            return;
                        }

                    }
                }
            }
            gestorDeConsultas.processFinished(String.format("Tramite: %d. SIN MATRICULA.", numeroDeTramite));
        } catch (Exception e){
            System.err.printf("Error al procesar el trámite: %d: %s", numeroDeTramite, e.getMessage());
            e.printStackTrace();
            gestorDeConsultas.processFinished(String.format("ERROR. Trámite: %d. Detalle: %s", numeroDeTramite, e.getMessage()));
        }
    }

    private boolean isMarkerLine(boolean markerLineFound, String line, int numero) {
        return markerLineFound || line.contains(String.valueOf(numero));
    }

    private boolean ignorarLinea(String linea) {
	    for(String lineaAIgnorar : LINES_TO_IGNORE) {
	        if (linea.contains(lineaAIgnorar)) {
	            return true;
            }
        }
        return false;
    }

    private String extract(String line, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        if(m.find()) {
            return m.group();
        }
        return null;
    }

}
