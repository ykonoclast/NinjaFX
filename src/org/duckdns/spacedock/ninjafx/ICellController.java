/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 *
 * @author ykonoclast
 */
public interface ICellController//TODO fusionner avec autres interfaces? Séparer les méthodes et en placer une sous une interface generique comme Consumer?
{

    public void setInitialElements(Runnable p_callback);

    public void updateItem(String p_newText, ReadOnlyDoubleProperty p_listViewWidth);
}
