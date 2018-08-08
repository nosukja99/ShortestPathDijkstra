import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * class for GUI of Graph 
 * @author hyejeongkim
 *
 */
public class GraphGUI extends Application{
	Button addTownB, addRoadB, findConnecB, readFileB, exit;
	ComboBox<String> comboSrc, comboDst, comboConnSrc, comboConnDst;
	TextArea  textArea = new TextArea ();
	
	TextField townName = new TextField();
	TextField roadName = new TextField();
	TextField distance = new TextField();
	Label labelTownName = new Label("Town Name:");
	Label labelRoadName = new Label("Road Name:");
	Label labelDistance = new Label("distance:");
	Label labelSelecT = new Label("Select towns for road");
	Label labelFindCon = new Label("Find connection from");
	Label labelTo = new Label("to");
	
	Label vboxTitleTop = new Label ("Add Town");
	Label vboxTitleMi = new Label ("Add Road");
	Label vboxTitleBot = new Label ("Find Connection");
	
	
	VBox vboxTop = new VBox();
	VBox vboxMi = new VBox();
	VBox vboxBot = new VBox();
	HBox hboxLast = new HBox();
	
	HBox hboxTop = new HBox();
	HBox hboxM1 = new HBox();
	HBox hboxM2 = new HBox();
	HBox hboxBot = new HBox();
	
	ToggleGroup group;
	
	TownGraphManager graph = null;
	
	Stage gstage=null;
	/**
	 * start main stage
	 */
	@Override
	public void start(Stage Stage) throws Exception {
		comboSrc = new ComboBox<>();
		//comboSrc.getItems();
		comboDst = new ComboBox<>();
		comboConnSrc = new ComboBox<>();
		comboConnDst = new ComboBox<>();
		
		addTownB = new Button ("Add Town");
		addRoadB = new Button ("Add Road");
		findConnecB = new Button ("Find Connectionn");
		readFileB=new Button ("Read File");
		exit = new Button ("Exit");
		
		graph = new TownGraphManager();
		
		addTownB.setOnAction(new ButtonEventHandler());
		addRoadB.setOnAction(new ButtonEventHandler());
		findConnecB.setOnAction(new ButtonEventHandler());
		readFileB.setOnAction(new ButtonEventHandler());
		exit.setOnAction(new ButtonEventHandler());
		
		GridPane MainPane = new GridPane();
		Insets insets=new Insets(10);
		Insets insets1=new Insets(20);
		
		hboxTop.getChildren().addAll(labelTownName, townName, addTownB);
		hboxTop.setMargin(labelTownName, insets);
		hboxTop.setMargin(townName, insets);
		hboxTop.setMargin(addTownB, insets);
		hboxM1.getChildren().addAll(labelRoadName, roadName);
		hboxM1.setMargin(labelRoadName, insets);
		hboxM1.setMargin(roadName, insets);
		hboxM2.getChildren().addAll(comboSrc, comboDst, labelDistance, distance, addRoadB);
		hboxM2.setMargin(comboSrc, insets);
		hboxM2.setMargin(comboDst, insets);
		hboxM2.setMargin(labelDistance, insets);
		hboxM2.setMargin(distance, insets);
		hboxM2.setMargin(addRoadB, insets);
		hboxBot.getChildren().addAll(labelFindCon, comboConnSrc, labelTo, comboConnDst, findConnecB);
		hboxBot.setMargin(labelFindCon, insets);
		hboxBot.setMargin(comboConnSrc, insets);
		hboxBot.setMargin(labelTo, insets);
		hboxBot.setMargin(comboConnDst, insets);
		hboxBot.setMargin(findConnecB, insets);
		
		vboxTop.getChildren().addAll(vboxTitleTop, hboxTop);
		vboxTop.setStyle("-fx-padding: 5;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1;" +
                "-fx-border-insets: 0;" + 
                "-fx-border-radius: 0;" + 
                "-fx-border-color: black;");
		//vboxTop.setAlignment();
		vboxMi.getChildren().addAll(vboxTitleMi, hboxM1,labelSelecT, hboxM2);
		vboxMi.setStyle("-fx-padding: 5;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1;" +
                "-fx-border-insets: 0;" + 
                "-fx-border-radius: 0;" + 
                "-fx-border-color: black;");
		vboxBot.getChildren().addAll(vboxTitleBot, hboxBot,textArea);
		vboxBot.setStyle("-fx-padding: 5;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1;" +
                "-fx-border-insets: 0;" + 
                "-fx-border-radius: 0;" + 
                "-fx-border-color: black;");
		hboxLast.getChildren().addAll(readFileB, exit);
		hboxLast.setMargin(readFileB, insets);
		hboxLast.setMargin(exit, insets);
		
		MainPane.add(vboxTop, 0, 0);
		MainPane.setMargin(vboxTop, insets1);
		MainPane.add(vboxMi, 0, 1);
		MainPane.setMargin(vboxMi, insets1);
		MainPane.add(vboxBot, 0, 2);
		MainPane.setMargin(vboxBot, insets1);
		MainPane.add(hboxLast, 0, 3);
		
		Scene scene = new Scene(MainPane, 700, 700); 
		
        Stage.setScene(scene);
		Stage.setTitle("Travelling Student");
		Stage.show();
		
	}
	/** 
	 * button event handler
	 * @author hyejeongkim
	 *
	 */
	private class ButtonEventHandler implements EventHandler<ActionEvent>
	{
        //Set<Town> towns = new HashSet<Town>();
		@Override
		public void handle(ActionEvent e) {
			if(e.getSource()==addTownB)
			{
				String stown = townName.getText();
				if(!stown.equals(""))
				{
				    graph.addTown(stown); 
					comboSrc.getItems().add(stown);
					comboDst.getItems().add(stown);
					comboConnSrc.getItems().add(stown); 
					comboConnDst.getItems().add(stown);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
			if(e.getSource()==addRoadB)
			{
				String selectiedTown1=comboSrc.getValue();
				String selectiedTown2=comboDst.getValue();
		        String dis = distance.getText();
				String road1 = roadName.getText();
				if(road1.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Road name cannot be blank");
				}
				else
				graph.addRoad(selectiedTown1, selectiedTown2, Integer.parseInt(dis), road1);
			}
			if(e.getSource()==findConnecB)
			{
				if(comboConnSrc.getSelectionModel().isEmpty() || comboConnDst.getSelectionModel().isEmpty())
					{
					JOptionPane.showMessageDialog(null, "Road name cannot be blank");

					return; 
					}
				String selectiedTown1=comboConnSrc.getValue();
				String selectiedTown2=comboConnDst.getValue();
				
				if(selectiedTown1.equals("") || selectiedTown2.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Road name cannot be blank");
				}else
				{
				//	graph.getPath(selectiedTown1, selectiedTown2);
					ArrayList<String> path = graph.getPath(selectiedTown1,selectiedTown2);
					String sText = "";
					for(int i=0;i<path.size();i++)
					{
						sText = sText + path.get(i) + "\n";
					}
					if(path.size()==0 || selectiedTown1.equals(selectiedTown2))
					{
						sText = "ERROR";
					}
					textArea.setText(sText);
				}

				///////////////////////////////////////////////////////////////////////
			}
			if(e.getSource()==readFileB)
			{
				File selectedFile = null;
				FileChooser chooser = new FileChooser();
				
				chooser.setTitle("Choose a file to read");
				if ((selectedFile = chooser.showOpenDialog(null)) != null) {

					try {
						Scanner S=new Scanner(selectedFile);
						String line;
						
							while (S.hasNextLine())
							{
								line = S.nextLine();

								String[] sRoadName = line.split(",");
								String[] sRoadExtra = sRoadName[1].split(";");

								if(graph.addTown(sRoadExtra[1]) == true)
								{
									comboConnSrc.getItems().add(sRoadExtra[1]); 
									comboConnDst.getItems().add(sRoadExtra[1]); 
								};
								if(graph.addTown(sRoadExtra[2])== true)
								{
									comboConnDst.getItems().add(sRoadExtra[2]);
									comboConnSrc.getItems().add(sRoadExtra[2]);
								}

								graph.addRoad(sRoadExtra[1], sRoadExtra[2], Integer.parseInt(sRoadExtra[0]), sRoadName[0]);

							}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getSource()==exit)
			{
				System.exit(0);
			}
		}

	}

	
	/**
	 * main to launch the GUI
	 * @param Strings args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
