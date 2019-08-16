/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Contrôle spécifiquement la vue réutilisable "DeletableCell"
 *
 * @author ykonoclast
 */
public class DeletableCellController implements ICellController
{

    /**
     * la cellule associée à ce contrôleur
     */
    private Runnable m_cellObject;

    //affectations des éléments fxml
    @FXML
    private Label fx_labelView;

    @FXML
    private HBox fx_hBox;

    /**
     * constructeur par défaut indispensable pour ne pas provoquer de bugs
     * d'initialisation avec certaines versions de javafx
     */
    public DeletableCellController()
    {
    }

    /**
     * appelé par le framework laissé ici aukazou
     */
    public void initialize()
    {

    }

    @FXML
    private void deleteAction(ActionEvent event)
    {
	m_cellObject.run();//c'est la cellule qui contrôle son cycle de vie et a accès à la ListView
    }

    @Override
    public void setInitialElements(Runnable p_runnable)
    {
	m_cellObject = p_runnable;//inscription de la Callback
    }

    @Override
    public void updateItem(String p_newText, ReadOnlyDoubleProperty p_listViewWidth)
    {
	fx_hBox.maxWidthProperty().bind(p_listViewWidth.subtract(20));//afin d'éviter qu'une scrollbar horizontale apparaisse, on ne laisse pas la Box dépasser en largeur les limites de la ListView, on laisse un peu de marge pour les ascenseurs et autres
	fx_labelView.setText(p_newText);
    }
}
