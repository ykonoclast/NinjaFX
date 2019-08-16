/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

/**
 * Vue réutilisable consistant en une cellule de ListView avec texte wrappable
 * et bouton de suppression
 *
 * @author ykonoclast
 */
public class DeletableCell extends ListCell<String> implements Runnable
{

    /**
     * le contrôleur graphique associé à cette cellule
     */
    private final ICellController m_controller;

    /**
     * le layout racine du fxml
     */
    private final Node m_root;

    /**
     * constructeur, charge le fxml et crache potentiellement une exception à
     * gérer plus haut
     *
     * @throws IOException
     */
    public DeletableCell() throws IOException
    {
	super();

	FXMLLoader loader = new FXMLLoader(getClass().getResource("DeletableCell.fxml"));//TODO encore un getclass

	m_root = loader.load();
	m_controller = (ICellController) loader.getController();
	m_controller.setInitialElements(this);//on s'inscrit comme Callback
	setGraphic(m_root);//on associe à cette Cellule le layout de plus haut niveau du xml
    }

    @Override
    protected void updateItem(String item, boolean empty)
    {
	super.updateItem(item, empty);

	if (item != null && !empty)//test standard nécessaire pour toutes les classes héritant de ListCell
	{
	    setGraphic(m_root);//on replace le root, il a probablement été vidé par le else précédemment
	    m_controller.updateItem(item, getListView().widthProperty());//le contrôleur se charge de la mise à jour de la partie graphique
	}
	else
	{//code standard pour chaque override de cette méthode
	    setText(null);
	    setGraphic(null);
	}
    }

    @Override
    public void run()//Callback utilisée par le contrôleur pour demander la suppression de la Cellule suite à pression sur le bouton
    {
	getListView().getItems().remove(getItem());
    }
}
