/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

/**
 *
 * @author ykonoclast
 */
public class Launcher
{

    /**
     * classe principale n'étendant pas Application pour éviter problème au
     * chargement sur certaines versions du jdk
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
	NinjaFX.main(args);
    }

}
