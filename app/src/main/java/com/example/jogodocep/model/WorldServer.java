package com.example.jogodocep.model;

import java.io.Serializable;

public class WorldServer implements Serializable {
    private int CEPTimeMachine;//CEP da máquina do tempo
    private int IPServer;//IP do servidor
    private int portaServer;//Porta do servidor
    private int nConectados;//número de jogadores conectados ao servidor

    public WorldServer(int CEPTimeMachine, int IPServer, int portaServer, int nConectados){
        this.CEPTimeMachine = CEPTimeMachine;
        this.IPServer = IPServer;
        this.portaServer = portaServer;
        this.nConectados = nConectados;
    }

    public int getCEPTimeMachine() {
        return CEPTimeMachine;
    }

    public void setCEPTimeMachine(int CEPTimeMachine) {
        this.CEPTimeMachine = CEPTimeMachine;
    }

    public int getIPServer() {
        return IPServer;
    }

    public void setIPServer(int IPServer) {
        this.IPServer = IPServer;
    }

    public int getPortaServer() {
        return portaServer;
    }

    public void setPortaServer(int portaServer) {
        this.portaServer = portaServer;
    }

    public int getnConectados() {
        return nConectados;
    }

    public void setnConectados(int nConectados) {
        this.nConectados = nConectados;
    }
}
