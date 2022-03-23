package application;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainController implements Initializable {
	@FXML
	HBox mainbox;
	@FXML
	HBox fixedBox;
	@FXML
	Label txtWurf;
	@FXML
	Button btn1er;
	@FXML
	Button btn2er;
	@FXML
	Button btn3er;
	@FXML
	Button btn4er;
	@FXML
	Button btn5er;
	@FXML
	Button btn6er;
	@FXML
	Button btnFullHouse;
	@FXML
	Button btnKlStr;
	@FXML
	Button btn3erPasch;
	@FXML
	Button btn4erPasch;
	@FXML
	Button btnGrStr;
	@FXML
	Button btnChance;
	@FXML
	Button btnKniffel;
	@FXML
	Button btnWuerfeln;
	@FXML
	Button btnInfo;
	@FXML
	Label txtPtOben;
	@FXML
	Label txtPtUnten;
	@FXML
	Label txtPtBonus;
	@FXML
	Label txtPtGesamt;
	@FXML
	MenuItem btnNeu;
	@FXML
	MenuItem btnAbout;

	int restbuttons = 13;

	@FXML
	void pressResultBtnZweier(ActionEvent e) {
		if (((Button) e.getSource()).getId().equals("btn2er")) {
			int punkte = 0;
			for (int i = 0; i < mainbox.getChildren().size(); i++) {

				if (((Wuerfel) mainbox.getChildren().get(i)).getZahl() == 1) {
					punkte += 2;
				}
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				if (((Wuerfel) fixedBox.getChildren().get(i)).getZahl() == 1) {
					punkte += 2;
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Vector<Wuerfel> wuerfel = new Vector<>();
		for (int i = 0; i < 5; i++) {
			wuerfel.add(new Wuerfel(60, Color.rgb(0xEF, 0xF3, 0xD8), Color.rgb(0xD6, 0xE1, 0x9C),
					Color.rgb(0x8E, 0x91, 0x23)));
			wuerfel.get(i).wuerfeln();
			wuerfel.get(i).setOnMouseClicked(e -> {

				if (mainbox.getChildren().contains((Wuerfel) e.getSource())) {
					mainbox.getChildren().remove((Wuerfel) e.getSource());
					fixedBox.getChildren().add((Wuerfel) e.getSource());
				} else {
					fixedBox.getChildren().remove((Wuerfel) e.getSource());
					mainbox.getChildren().add((Wuerfel) e.getSource());
				}

			});
		}

		mainbox.getChildren().addAll(wuerfel);
		setHandler();

	}

	public void highscore() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("highscore.txt"));
			String line = "";
			String alles = "";
			while ((line = br.readLine()) != null) {
				alles += line+"\n";
			}
			
			JOptionPane.showMessageDialog(null, alles);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void wuerfeln() {
		for (int i = 0; i < mainbox.getChildren().size(); i++) {
			((Wuerfel) mainbox.getChildren().get(i)).wuerfeln();
		}
		if (Integer.parseInt(txtWurf.getText()) < 3) {
			txtWurf.setText((Integer.parseInt(txtWurf.getText()) + 1) + "");
		}
		if (Integer.parseInt(txtWurf.getText()) == 3) {
			btnWuerfeln.setDisable(true);
		}
	}

	public void wuerfeln(boolean reset) {
		for (int i = 0; i < mainbox.getChildren().size(); i++) {
			((Wuerfel) mainbox.getChildren().get(i)).wuerfeln();
		}
		if (Integer.parseInt(txtWurf.getText()) < 3) {
			txtWurf.setText((Integer.parseInt(txtWurf.getText()) + 1) + "");
		}
		if (Integer.parseInt(txtWurf.getText()) == 3 && !reset) {
			btnWuerfeln.setDisable(true);
		}
	}

	public void setHandler() {
		this.btn1er.setOnAction(e -> {
			int punkte = 0;
			for (int i = 0; i < mainbox.getChildren().size(); i++) {

				if (((Wuerfel) mainbox.getChildren().get(i)).getZahl() == 1) {
					punkte += 1;
				}
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				if (((Wuerfel) fixedBox.getChildren().get(i)).getZahl() == 1) {
					punkte += 1;
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);

			txtPtOben.setText((Integer.parseInt(txtPtOben.getText()) + punkte) + "");
			resetCounter();
		});

		this.btn2er.setOnAction(e -> {
			int punkte = 0;
			int gesucht = 2;
			for (int i = 0; i < mainbox.getChildren().size(); i++) {

				if (((Wuerfel) mainbox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				if (((Wuerfel) fixedBox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtOben.setText((Integer.parseInt(txtPtOben.getText()) + punkte) + "");
			resetCounter();
		});

		this.btn3er.setOnAction(e -> {
			int punkte = 0;
			int gesucht = 3;
			for (int i = 0; i < mainbox.getChildren().size(); i++) {

				if (((Wuerfel) mainbox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				if (((Wuerfel) fixedBox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtOben.setText((Integer.parseInt(txtPtOben.getText()) + punkte) + "");
			resetCounter();
		});

		this.btn4er.setOnAction(e -> {
			int punkte = 0;
			int gesucht = 4;
			for (int i = 0; i < mainbox.getChildren().size(); i++) {

				if (((Wuerfel) mainbox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				if (((Wuerfel) fixedBox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtOben.setText((Integer.parseInt(txtPtOben.getText()) + punkte) + "");
			resetCounter();
		});

		this.btn5er.setOnAction(e -> {
			int punkte = 0;
			int gesucht = 5;
			for (int i = 0; i < mainbox.getChildren().size(); i++) {

				if (((Wuerfel) mainbox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				if (((Wuerfel) fixedBox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtOben.setText((Integer.parseInt(txtPtOben.getText()) + punkte) + "");
			resetCounter();
		});

		this.btn6er.setOnAction(e -> {
			int punkte = 0;
			int gesucht = 6;
			for (int i = 0; i < mainbox.getChildren().size(); i++) {

				if (((Wuerfel) mainbox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				if (((Wuerfel) fixedBox.getChildren().get(i)).getZahl() == gesucht) {
					punkte += gesucht;
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtOben.setText((Integer.parseInt(txtPtOben.getText()) + punkte) + "");
			resetCounter();
		});

		this.btn3erPasch.setOnAction(e -> {

			int punkte = 0;
			int[] zahlen = new int[6];
			for (int i = 0; i < zahlen.length; i++) {
				zahlen[i] = 0;
			}

			for (int i = 0; i < mainbox.getChildren().size(); i++) {
				zahlen[((Wuerfel) mainbox.getChildren().get(i)).getZahl() - 1]++;
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				zahlen[((Wuerfel) fixedBox.getChildren().get(i)).getZahl() - 1]++;
			}

			for (int i = 0; i < zahlen.length; i++) {
				if (zahlen[i] >= 3) {
					for (int j = 0; j < zahlen.length; j++) {
						punkte = punkte + zahlen[j] * (j + 1);
					}
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtUnten.setText((Integer.parseInt(txtPtUnten.getText()) + punkte) + "");
			resetCounter();
		});

		this.btn4erPasch.setOnAction(e -> {
			int punkte = 0;
			int[] zahlen = new int[6];
			for (int i = 0; i < zahlen.length; i++) {
				zahlen[i] = 0;
			}

			for (int i = 0; i < mainbox.getChildren().size(); i++) {
				zahlen[((Wuerfel) mainbox.getChildren().get(i)).getZahl() - 1]++;
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				zahlen[((Wuerfel) fixedBox.getChildren().get(i)).getZahl() - 1]++;
			}

			for (int i = 0; i < zahlen.length; i++) {
				if (zahlen[i] >= 4) {
					for (int j = 0; j < zahlen.length; j++) {
						punkte = punkte + zahlen[j] * (j + 1);
					}
				}
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtUnten.setText((Integer.parseInt(txtPtUnten.getText()) + punkte) + "");
			resetCounter();
		});

		this.btnFullHouse.setOnAction(e -> {
			int punkte = 0;
			int[] zahlen = new int[6];
			boolean doppel = false;
			boolean dreifach = false;
			for (int i = 0; i < zahlen.length; i++) {
				zahlen[i] = 0;
			}

			for (int i = 0; i < mainbox.getChildren().size(); i++) {
				zahlen[((Wuerfel) mainbox.getChildren().get(i)).getZahl() - 1]++;
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				zahlen[((Wuerfel) fixedBox.getChildren().get(i)).getZahl() - 1]++;
			}

			for (int i = 0; i < zahlen.length; i++) {
				if (zahlen[i] == 2) {
					doppel = true;
				}
				if (zahlen[i] == 3) {
					dreifach = true;
				}
			}

			if (doppel && dreifach) {
				punkte = 25;
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtUnten.setText((Integer.parseInt(txtPtUnten.getText()) + punkte) + "");
			resetCounter();
		});

		this.btnKlStr.setOnAction(e -> {
			int punkte = 0;
			int[] zahlen = new int[6];
			for (int i = 0; i < zahlen.length; i++) {
				zahlen[i] = 0;
			}

			for (int i = 0; i < mainbox.getChildren().size(); i++) {
				zahlen[((Wuerfel) mainbox.getChildren().get(i)).getZahl() - 1]++;
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				zahlen[((Wuerfel) fixedBox.getChildren().get(i)).getZahl() - 1]++;
			}

			boolean allesKleinerGleich2 = true;
			for (int i = 0; i < zahlen.length; i++) {
				if (zahlen[i] > 2) {
					allesKleinerGleich2 = false;
				}
			}

			if (allesKleinerGleich2) {
				punkte = 30;
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtUnten.setText((Integer.parseInt(txtPtUnten.getText()) + punkte) + "");
			resetCounter();
		});

		this.btnGrStr.setOnAction(e -> {
			int punkte = 0;
			int[] zahlen = new int[6];
			for (int i = 0; i < zahlen.length; i++) {
				zahlen[i] = 0;
			}

			for (int i = 0; i < mainbox.getChildren().size(); i++) {
				zahlen[((Wuerfel) mainbox.getChildren().get(i)).getZahl() - 1]++;
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				zahlen[((Wuerfel) fixedBox.getChildren().get(i)).getZahl() - 1]++;
			}

			boolean allesKleinerGleich1 = true;
			for (int i = 0; i < zahlen.length; i++) {
				if (zahlen[i] > 1) {
					allesKleinerGleich1 = false;
				}
			}

			if (allesKleinerGleich1) {
				punkte = 40;
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtUnten.setText((Integer.parseInt(txtPtUnten.getText()) + punkte) + "");
			resetCounter();
		});

		this.btnChance.setOnAction(e -> {
			int punkte = 0;
			int[] zahlen = new int[6];
			for (int i = 0; i < zahlen.length; i++) {
				zahlen[i] = 0;
			}

			for (int i = 0; i < mainbox.getChildren().size(); i++) {
				zahlen[((Wuerfel) mainbox.getChildren().get(i)).getZahl() - 1]++;
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				zahlen[((Wuerfel) fixedBox.getChildren().get(i)).getZahl() - 1]++;
			}

			for (int i = 0; i < zahlen.length; i++) {
				punkte = punkte + (i + 1) * zahlen[i];
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtUnten.setText((Integer.parseInt(txtPtUnten.getText()) + punkte) + "");
			resetCounter();
		});

		this.btnKniffel.setOnAction(e -> {
			int punkte = 0;
			int[] zahlen = new int[6];
			for (int i = 0; i < zahlen.length; i++) {
				zahlen[i] = 0;
			}

			for (int i = 0; i < mainbox.getChildren().size(); i++) {
				zahlen[((Wuerfel) mainbox.getChildren().get(i)).getZahl() - 1]++;
			}
			for (int i = 0; i < fixedBox.getChildren().size(); i++) {
				zahlen[((Wuerfel) fixedBox.getChildren().get(i)).getZahl() - 1]++;
			}

			boolean alles1 = true;
			for (int i = 0; i < zahlen.length; i++) {
				if (zahlen[i] != 0 && zahlen[i] != 5) {
					alles1 = false;
				}
			}

			if (alles1) {
				punkte = 50;
			}

			((Button) e.getSource()).setText(punkte + "");
			((Button) e.getSource()).setDisable(true);
			txtPtUnten.setText((Integer.parseInt(txtPtUnten.getText()) + punkte) + "");
			resetCounter();
		});

		this.btnInfo.setOnAction(e -> {
			JOptionPane.showMessageDialog(null,
					"3er Pasch: Alle Augen zaehlen\n4er Pasch: Alle Augen zaehlen\nChance: Alle Augen zaehlen\nFull House: 25pt\nKleine Strasse: 30pt\nGrosse Strasse: 40pt\nKniffel: 50pt");
		});

		this.btnNeu.setOnAction(e -> {
			btn1er.setText("1er");
			btn2er.setText("2er");
			btn3er.setText("3er");
			btn4er.setText("4er");
			btn5er.setText("5er");
			btn6er.setText("6er");
			btn3erPasch.setText("Dreierpasch");
			btn4erPasch.setText("Viererpasch");
			btnFullHouse.setText("Full-House");
			btnKlStr.setText("Kl. Straße");
			btnGrStr.setText("Gr. Straße");
			btnChance.setText("Chance");
			btnKniffel.setText("Kniffel");
			btn1er.setDisable(false);
			btn2er.setDisable(false);
			btn3er.setDisable(false);
			btn4er.setDisable(false);
			btn5er.setDisable(false);
			btn6er.setDisable(false);
			btn3erPasch.setDisable(false);
			btn4erPasch.setDisable(false);
			btnFullHouse.setDisable(false);
			btnKlStr.setDisable(false);
			btnGrStr.setDisable(false);
			btnChance.setDisable(false);
			btnKniffel.setDisable(false);
			txtPtBonus.setText("0");
			txtPtGesamt.setText("0");
			txtPtOben.setText("0");
			txtPtUnten.setText("0");
			btnWuerfeln.setDisable(false);
			mainbox.getChildren().addAll(fixedBox.getChildren());
			fixedBox.getChildren().clear();
			wuerfeln(true);
			txtWurf.setText("1");
			restbuttons = 13;
		});

		this.btnAbout.setOnAction(e -> {
			JOptionPane.showMessageDialog(null,
					"Entwickler: Marc Wallitschek\nVersion:0.0.1\nUpdate Datum: 24.02.2022");
		});
	}

	public void resetCounter() {
		mainbox.getChildren().addAll(fixedBox.getChildren());
		fixedBox.getChildren().clear();
		wuerfeln(false);
		txtWurf.setText(1 + "");
		btnWuerfeln.setDisable(false);
		if (Integer.parseInt(txtPtOben.getText()) > 62) {
			txtPtBonus.setText(35 + "");
		}
		txtPtGesamt.setText((Integer.parseInt(txtPtOben.getText()) + Integer.parseInt(txtPtUnten.getText())
				+ Integer.parseInt(txtPtBonus.getText())) + "");
		restbuttons--;
		if (restbuttons == 0) {
			JOptionPane.showMessageDialog(null,
					"Herzlichen Glückwunsch, du hast " + txtPtGesamt.getText() + " Punkte erreicht");
			btnWuerfeln.setDisable(true);
			try {
				BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));
				String line = "";
				Vector<Integer> punkte = new Vector<>();
				boolean added = false;
				while ((line = br.readLine()) != null) {
					if (Integer.parseInt(line) < Integer.parseInt(txtPtGesamt.getText()) && !added) {
						punkte.add(Integer.parseInt(txtPtGesamt.getText()));
						added = true;
					}
					punkte.add(Integer.parseInt(line));
				}
				if(!added) {
					punkte.add(Integer.parseInt(txtPtGesamt.getText()));
				}
				br.close();
				BufferedWriter bw = new BufferedWriter(new FileWriter("highscore.txt"));
				for (Integer cur : punkte) {
					bw.append(cur + "\n");
				}
				bw.close();
			} catch (FileNotFoundException e) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("highscore.txt"));
					bw.append(txtPtGesamt.getText());
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
