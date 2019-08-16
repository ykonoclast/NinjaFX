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
public interface ICellController//TODO fusionner avec autres interfaces? Séparer les méthodes et en placer une sous une interface generique comme Consumer?
{

    public void setCallback(Runnable p_runnable);

    public void updateItem(String p_newText);
}
