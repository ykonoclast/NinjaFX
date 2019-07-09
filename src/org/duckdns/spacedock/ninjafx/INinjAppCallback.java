/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;

/**
 *
 * @author ykonoclast
 */
public interface INinjAppCallback
{

    public void displayMenu() throws IOException;

    public void displayGameScreen() throws IOException;
}
