import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class window {

	static WeatherApp weather;
	private JFrame frmWeatherApp;
	private JLabel lblTemperature;
	private JLabel lblTemperatureNum;
	private JButton btnRefresh;
	private JTextField textField_zipCode;
	private JLabel lblZipCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			weather = new WeatherApp();
		} catch (IOException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frmWeatherApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWeatherApp = new JFrame();
		frmWeatherApp.setTitle("Weather App");
		frmWeatherApp.setBounds(100, 100, 350, 233);
		frmWeatherApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 66, 81, 0};
		gridBagLayout.rowHeights = new int[]{62, 20, 14, 34, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmWeatherApp.getContentPane().setLayout(gridBagLayout);
		
		lblZipCode = new JLabel("ZIP Code:");
		GridBagConstraints gbc_lblZipCode = new GridBagConstraints();
		gbc_lblZipCode.anchor = GridBagConstraints.EAST;
		gbc_lblZipCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblZipCode.gridx = 1;
		gbc_lblZipCode.gridy = 1;
		frmWeatherApp.getContentPane().add(lblZipCode, gbc_lblZipCode);
		
		textField_zipCode = new JTextField();
		GridBagConstraints gbc_textField_zipCode = new GridBagConstraints();
		gbc_textField_zipCode.anchor = GridBagConstraints.NORTH;
		gbc_textField_zipCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_zipCode.insets = new Insets(0, 0, 5, 0);
		gbc_textField_zipCode.gridx = 2;
		gbc_textField_zipCode.gridy = 1;
		frmWeatherApp.getContentPane().add(textField_zipCode, gbc_textField_zipCode);
		textField_zipCode.setColumns(10);
		
		lblTemperature = new JLabel("Temperature:");
		GridBagConstraints gbc_lblTemperature = new GridBagConstraints();
		gbc_lblTemperature.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTemperature.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemperature.gridx = 1;
		gbc_lblTemperature.gridy = 2;
		frmWeatherApp.getContentPane().add(lblTemperature, gbc_lblTemperature);
		
		lblTemperatureNum = new JLabel("0 F");
		GridBagConstraints gbc_lblTemperatureNum = new GridBagConstraints();
		gbc_lblTemperatureNum.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTemperatureNum.insets = new Insets(0, 0, 5, 0);
		gbc_lblTemperatureNum.gridx = 2;
		gbc_lblTemperatureNum.gridy = 2;
		frmWeatherApp.getContentPane().add(lblTemperatureNum, gbc_lblTemperatureNum);
		
		
		//Refresh Button
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Read from text boxes
				//State
				/*
				if (!(textField_State.getText().equals("")))
					weather.setState(textField_State.getText());
				
				//City
				if (!(textField_City.getText().equals("")))
					weather.setCity(textField_City.getText());
				*/
				
				
				//Postal Code
				if (!(textField_zipCode.getText().equals("")))
					weather.setZipCode(textField_zipCode.getText());
				
				//Gather updated data
				try {
					weather.refreshData();
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Return temperature in fahrenheit
				try {
					lblTemperatureNum.setText(weather.getTemperature());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.anchor = GridBagConstraints.NORTH;
		gbc_btnRefresh.gridwidth = 2;
		gbc_btnRefresh.gridx = 1;
		gbc_btnRefresh.gridy = 4;
		frmWeatherApp.getContentPane().add(btnRefresh, gbc_btnRefresh);
		
	}

}
