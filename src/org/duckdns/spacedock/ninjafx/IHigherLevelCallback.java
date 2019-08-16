/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import javafx.scene.Parent;

/**
 *
 * @author ykonoclast
 */
public interface IHigherLevelCallback
{
//todo généraliser et remplacer le displayScene par un accept peut être

    public void displayScene(String p_fxmlFileName) throws IOException;
}
