/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Kupac;
import domain.Porudzbina;
import domain.Prodavac;
import domain.Proizvodjac;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

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
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_KUPAC:
                    ServerController.getInstance().addKupac((Kupac) request.getData());
                    break;
                case Operation.ADD_PORUDZBINA:
                    ServerController.getInstance().addPorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.DELETE_KUPAC:
                    ServerController.getInstance().deleteKupac((Kupac) request.getData());
                    break;
                case Operation.DELETE_PORUDZBINA:
                    ServerController.getInstance().deletePorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.UPDATE_KUPAC:
                    ServerController.getInstance().updateKupac((Kupac) request.getData());
                    break;
                case Operation.UPDATE_PORUDZBINA:
                    ServerController.getInstance().updatePorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.GET_ALL_KUPAC:
                    response.setData(ServerController.getInstance().getAllKupac());
                    break;
                case Operation.GET_ALL_PORUDZBINA:
                    response.setData(ServerController.getInstance().getAllPorudzbina());
                    break;
                case Operation.GET_ALL_PRODAVAC:
                    response.setData(ServerController.getInstance().getAllProdavac());
                    break;
                case Operation.GET_ALL_PROIZVODJAC:
                    response.setData(ServerController.getInstance().getAllProizvodjac());
                    break;
                case Operation.GET_ALL_STAVKA_PORUDZBINE:
                    response.setData(ServerController.getInstance().getAllStavkaPorudzbine((Porudzbina) request.getData()));
                    break;
                case Operation.GET_ALL_TELEFON:
                    response.setData(ServerController.getInstance().getAllTelefon((Proizvodjac) request.getData()));
                    break;
                case Operation.LOGIN:
                    Prodavac prodavac = (Prodavac) request.getData();
                    Prodavac ulogovani = ServerController.getInstance().login(prodavac);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }

}
