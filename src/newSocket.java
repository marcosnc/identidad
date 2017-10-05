// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   newSocket.java

import java.io.*;
import java.net.*;
import java.net.Proxy.Type;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class newSocket extends Thread
{
	private Proxy proxy;
	private String address;
	
	public newSocket(String address, int n, String d, String m, int a, String proxyHost, String proxyPort)
    {
        resultado = "";
        this.direccion = null;
        numero = n;
        dia = d;
        mes = m;
        anio = a;
        this.address = address;
        InetAddress direccion = null;
        
        try
        {
        	if( proxyHost==null || "".equals(proxyHost) ){
                direccion = InetAddress.getByName(address);
                s1 = new Socket(direccion, 80);
                s1.setSoTimeout(6000);
                escritura = new DataOutputStream(s1.getOutputStream());
                lectura = new DataInputStream(s1.getInputStream());
                this.proxy=null;
        	} else {
    			this.proxy = new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(proxyHost, Integer.parseInt(proxyPort)));
    			
    			s1=null;
    			escritura = null;
    			lectura   = null;
        	}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
        	if( proxy==null ){
                String mensaje = (new StringBuilder("GET /seguitra/con_seguimi.php?numentrada=")).append(numero).append("&dia=").append(dia).append("&mes=").append(mes).append("&ano=").append(anio).append("&=Enviar+la+consulta+ HTTP/1.0\r\n").toString();
                mensaje = (new StringBuilder(String.valueOf(mensaje))).append("Host:www.dnrpi.jus.gov.ar\r\n").toString();
                mensaje = (new StringBuilder(String.valueOf(mensaje))).append("User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.30 Safari/525.13\r\n").toString();
                mensaje = (new StringBuilder(String.valueOf(mensaje))).append("Connection: Close\r\n\r\n").toString();
                escritura.writeBytes(mensaje);
        	} else {
//        		String completeAddress = "http://" + address + "/seguitra/con_seguimi.php?numentrada="+numero+"&dia="+dia+"&mes="+mes+"&ano="+anio; 
//    			URL url = new URL(completeAddress);
//    			URLConnection conn = url.openConnection(this.proxy);
//    			lectura = new DataInputStream(conn.getInputStream());
    			
    			String rawData = "numentrada="+numero+"&dia="+dia+"&mes="+mes+"&ano="+anio;
    			String type = "application/x-www-form-urlencoded";
    			String encodedData = URLEncoder.encode( rawData, "UTF-8" ); 
//    			URL u = new URL("http://" + address + "/seguitra/con_seguimi.php");
    			URL u = new URL(address + "/con_seguimi.php");
    			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
    			conn.setDoOutput(true);
    			conn.setRequestMethod( "POST" );
    			conn.setRequestProperty( "Content-Type", type );
    			conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()) );
    			OutputStream os = conn.getOutputStream();
    			os.write( encodedData.getBytes() );
        	}
        }
        catch(Exception e) {
        	e.printStackTrace();        	
        }
    }

    private void proceso(String linea)
    {
        Pattern p = Pattern.compile("Por favor, verifique los datos e intente nuevamente");
        Matcher m = p.matcher(linea);
        if(m.find())
        {
            resultado = (new StringBuilder("No existe el numero de entrada ")).append(numero).toString();
            return;
        }
        p = Pattern.compile("retirar su");
        m = p.matcher(linea);
        if(m.find())
        {
            resultado = (new StringBuilder("El numero de entrada ")).append(numero).append(" ya se emitio").toString();
            return;
        }
        p = Pattern.compile("[0-9]{1,2}-[0-9]{1,5}([/,0-9,\\-]{1,5})");
        m = p.matcher(linea);
        if(m.find())
        {
        	/* DESCOMENTAR!!!
            dbGestor db = new dbGestor(Identidad.properties.getProperty("dns"));
            String matricula = m.group();
            String num = rutinas.sacoCeros(matricula);
            String mes2 = rutinas.getNumByMes(mes);
            String fecha = (new StringBuilder(String.valueOf(dia))).append("/").append(mes2).append("/").append(String.valueOf(anio).substring(2, 4)).toString();
            db.inserTo(num, fecha);
            resultado = (new StringBuilder("El numero de entrada ")).append(numero).append(" se agrego a la base de datos con Matricula ").append(num).append(" y fecha ").append(fecha).toString();
            return;
            */
            String matricula = m.group();
            String num = rutinas.sacoCeros(matricula);
            String mes2 = rutinas.getNumByMes(mes);
            String fecha = (new StringBuilder(String.valueOf(dia))).append("/").append(mes2).append("/").append(String.valueOf(anio).substring(2, 4)).toString();
            resultado = (new StringBuilder("El numero de entrada ")).append(numero).append(" se agrego a la base de datos con Matricula ").append(num).append(" y fecha ").append(fecha).toString();
            System.out.println("A: " + resultado);
        }
        p = Pattern.compile("horario");
        m = p.matcher(linea);
        if(m.find())
        {
        	/* DESCOMENTAR!!!
            dbGestor db = new dbGestor(Identidad.properties.getProperty("dns"));
            String matricula = m.group();
            String num = rutinas.sacoCeros(matricula);
            String mes2 = rutinas.getNumByMes(mes);
            String fecha = (new StringBuilder(String.valueOf(dia))).append("/").append(mes2).append("/").append(String.valueOf(anio).substring(2, 4)).toString();
            db.inserTo(num, fecha);
            resultado = "No disponible";
            return;
            */
            String matricula = m.group();
            String num = rutinas.sacoCeros(matricula);
            String mes2 = rutinas.getNumByMes(mes);
            String fecha = (new StringBuilder(String.valueOf(dia))).append("/").append(mes2).append("/").append(String.valueOf(anio).substring(2, 4)).toString();
            resultado = "No disponible";
            System.out.println("B: " + resultado + "   " + num + "   " + fecha);
        } else
        {
            return;
        }
    }

    public String getResult()
    {
        String linea = "";
        try
        {
            while((linea = lectura.readLine()) != null) 
                proceso(linea);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } finally {
        	try {
        		if( lectura!=null ){
    				lectura.close();
        		}
	        	if( s1!=null ){
	        		s1.close();
	        	}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return resultado;
    }

    public DataInputStream lectura;
    public DataOutputStream escritura;
    public String resultado;
    public InetAddress direccion;
    public int anio;
    public int numero;
    public String dia;
    public String mes;
    public Socket s1;
}
