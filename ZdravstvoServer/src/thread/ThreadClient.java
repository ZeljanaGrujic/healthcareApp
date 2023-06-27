/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Doktor;
import domain.Pacijent;
import domain.StavkaTermina;
import domain.Termin;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import jdk.nashorn.internal.parser.TokenType;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_PACIJENT:
                    ServerController.getInstance().addPacijent((Pacijent) req.getData());
                    break;
                case Operation.ADD_DOKTOR:
                    ServerController.getInstance().addDoktor((Doktor) req.getData());
                    break;
                case Operation.ADD_TERMIN:
                    ServerController.getInstance().addTermin((Termin) req.getData());
                    break;
                case Operation.ADD_STAVKA_TERMINA:
                    ServerController.getInstance().addStavkaTermina((StavkaTermina) req.getData());
                    break;
                case Operation.DELETE_PACIJENT:
                    ServerController.getInstance().deletePacijent((Pacijent) req.getData());
                    break;
                case Operation.DELETE_TERMIN:
                    ServerController.getInstance().deleteTermin((Termin) req.getData());
                    break;
                case Operation.DELETE_STAVKA_TERMINA:
                    ServerController.getInstance().deleteStavkaTermina((StavkaTermina) req.getData());
                    break;
                case Operation.UPDATE_PACIJENT:
                    ServerController.getInstance().updatePacijent((Pacijent) req.getData());
                    break;
                case Operation.UPDATE_TERMIN:
                    ServerController.getInstance().updateTermin((Termin) req.getData());
                    break;
                case Operation.GET_ALL_DOKTOR:
                    res.setData(ServerController.getInstance().getAllDoktor());
                    break;
                case Operation.GET_ALL_PACIJENT:
                    res.setData(ServerController.getInstance().getAllPacijent());
                    break;
                case Operation.GET_ALL_TERMIN:
                    res.setData(ServerController.getInstance().getAllTermin((Pacijent) req.getData()));
                    break;
                case Operation.GET_ALL_USLUGA:
                    res.setData(ServerController.getInstance().getAllUsluga());
                    break;
                case Operation.GET_ALL_ZDRAVSTVENA_ORGANIZACIJA:
                    res.setData(ServerController.getInstance().getAllOrganizacija());
                    break;
                case Operation.GET_ALL_STAVKA_TERMINA:
                    res.setData(ServerController.getInstance().getAllStavkaTermina((Termin) req.getData()));
                    break;
                case Operation.LOGIN:
                    ArrayList<Doktor> lista = ServerController.getInstance().getAllDoktor();
                    Doktor d = (Doktor) req.getData();
                    for (Doktor doktor : lista) {
                        if (doktor.getUsername().equals(d.getUsername())
                                && doktor.getPassword().equals(d.getPassword())) {
                            res.setData(doktor);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("Ne postoji doktor sa tim kredencijalima.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
