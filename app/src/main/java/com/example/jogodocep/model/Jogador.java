package com.example.jogodocep.model;

import java.io.Serializable;

public class Jogador implements Serializable {
    private int IP;//endereço IP do jogador
    private int porta;//porta do jogador
    private int CEP;//CEP onde está o jogador
    private int nPontos;//número de pontos do jogador
    private int nTentativas;//número de tentativas do jogador
    private int tempoDoJogador;//tempo que se passou desde que o jogador começou a jogar até acertar o CEP
    private boolean isServer;//se o jogador não for servidor, é um cliente
    private boolean isMorlock;//se o jogador não for um Morlock, é um Eloi

/*    public Jogador(int IP, int porta, int CEP, int nPontos, int nTentativas, int tempoDoJogador, boolean isServer, boolean isMorlock){
        this.IP = IP;
        this.porta = porta;
        this.CEP = CEP;
        this.nPontos = nPontos;
        this.nTentativas = nTentativas;
        this.tempoDoJogador = tempoDoJogador;
        this.isServer = isServer;
        this.isMorlock = isMorlock;
    }*/

    public int getIP() {
        return IP;
    }

    public void setIP(int IP) {
        this.IP = IP;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public int getnPontos() {
        return nPontos;
    }

    public void setnPontos(int nPontos) {
        this.nPontos = nPontos;
    }

    public int getnTentativas() {
        return nTentativas;
    }

    public void setnTentativas(int nTentativas) {
        this.nTentativas = nTentativas;
    }

    public int getTempoDoJogador() {
        return tempoDoJogador;
    }

    public void setTempoDoJogador(int tempoDoJogador) {
        this.tempoDoJogador = tempoDoJogador;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public boolean isMorlock() {
        return isMorlock;
    }

    public void setMorlock(boolean morlock) {
        isMorlock = morlock;
    }
}
