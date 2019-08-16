/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.duckdns.spacedock.ninjafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author ykonoclast
 */
public class DeletableCellController implements ICellController
{

    private Runnable m_callBack;

    //affectations des éléments fxml
    @FXML
    private Label fx_labelView;

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
	m_callBack.run();

    }

    @Override
    public void setCallback(Runnable p_runnable)
    {
	m_callBack = p_runnable;
    }

    @Override
    public void updateItem(String p_newText)
    {

	fx_labelView.setText(p_newText);
    }

}
