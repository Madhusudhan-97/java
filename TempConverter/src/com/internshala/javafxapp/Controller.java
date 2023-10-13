package com.internshala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final String c_to_f="Celcius to Fahreinheit";
	private static final String f_to_c="Fahrenheit to Celcius";
	private boolean isC_to_F = true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceBox.getItems().add(c_to_f);
		choiceBox.getItems().add(f_to_c);
		choiceBox.setValue(c_to_f);
		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals(c_to_f)){
				isC_to_F= true;
			}
			else{
				isC_to_F=false;
			}
		});
		convertButton.setOnAction(event -> {
			convert();
		});
	}

	private void convert() {
		String input=userInputField.getText();
		float enteredTemp=0.0f;
		try{
			enteredTemp= Float.parseFloat(input);
		}
		catch (Exception ex){
			warnUser();
			return;
		}

		float newTemp=0.0f;
		if(isC_to_F){
			newTemp=(enteredTemp*9/5)+32;
		}
		else{
			newTemp=(enteredTemp-32)*5/9;
		}
		display(newTemp);
	}

	private void warnUser() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("INNVALID TEMPERATURE ENTERED");
		alert.setContentText("Please enter a valid temperature");
		alert.show();
	}

	private void display(float newTemp) {
		String unit = isC_to_F?"F":"C";
		System.out.println("The new Temperature is: "+newTemp+unit);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new Temperature is: "+newTemp+unit);
		alert.show();
	}
}
