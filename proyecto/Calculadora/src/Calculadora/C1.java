package Calculadora;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class C1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumero1;
	private JTextField txtNumero2;
	private JTextField txtRespuesta;
	private JTextArea txtS;
	private JToggleButton tglbtnsumar, tglbtnresta, tglbtnmultiplicacion, tglbtndividir, tglbtnporcentaje;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				C1 frame = new C1();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public C1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("CALCULADORA");
		lblTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblTitulo.setBounds(250, 20, 150, 20);
		contentPane.add(lblTitulo);

		JLabel lblNum1 = new JLabel("NÚMERO 1");
		lblNum1.setBounds(100, 70, 80, 15);
		contentPane.add(lblNum1);

		JLabel lblNum2 = new JLabel("NÚMERO 2");
		lblNum2.setBounds(300, 70, 80, 15);
		contentPane.add(lblNum2);

		txtNumero1 = new JTextField();
		txtNumero1.setBounds(100, 90, 100, 20);
		contentPane.add(txtNumero1);
		txtNumero1.setColumns(10);

		txtNumero2 = new JTextField();
		txtNumero2.setBounds(300, 90, 100, 20);
		contentPane.add(txtNumero2);
		txtNumero2.setColumns(10);

		txtRespuesta = new JTextField();
		txtRespuesta.setBounds(480, 90, 100, 20);
		contentPane.add(txtRespuesta);

		JLabel lblRespuesta = new JLabel("RESPUESTA");
		lblRespuesta.setBounds(480, 70, 100, 15);
		contentPane.add(lblRespuesta);

		// Toggle Buttons
		tglbtnsumar = new JToggleButton("+");
		tglbtnresta = new JToggleButton("-");
		tglbtnmultiplicacion = new JToggleButton("x");
		tglbtndividir = new JToggleButton("/");
		tglbtnporcentaje = new JToggleButton("%");

		tglbtnsumar.setBounds(100, 130, 60, 25);
		tglbtnresta.setBounds(170, 130, 60, 25);
		tglbtnmultiplicacion.setBounds(240, 130, 60, 25);
		tglbtndividir.setBounds(310, 130, 60, 25);
		tglbtnporcentaje.setBounds(380, 130, 60, 25);

		contentPane.add(tglbtnsumar);
		contentPane.add(tglbtnresta);
		contentPane.add(tglbtnmultiplicacion);
		contentPane.add(tglbtndividir);
		contentPane.add(tglbtnporcentaje);

		ButtonGroup grupoOperaciones = new ButtonGroup();
		grupoOperaciones.add(tglbtnsumar);
		grupoOperaciones.add(tglbtnresta);
		grupoOperaciones.add(tglbtnmultiplicacion);
		grupoOperaciones.add(tglbtndividir);
		grupoOperaciones.add(tglbtnporcentaje);

		JButton btnResultado = new JButton("RESULTADO");
		btnResultado.setBounds(230, 170, 150, 25);
		contentPane.add(btnResultado);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 210, 460, 200);
		contentPane.add(scrollPane);

		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		txtS.setEditable(false);

		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(400, 170, 100, 25);
		contentPane.add(btnLimpiar);

		btnResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double n1 = Double.parseDouble(txtNumero1.getText());
					double n2 = Double.parseDouble(txtNumero2.getText());
					
					if (txtRespuesta.getText().trim().isEmpty()) {
					    txtS.setText("Por favor, ingresa tu respuesta antes de presionar RESULTADO.");
					    return;
					}
					
					double respuestaUsuario = Double.parseDouble(txtRespuesta.getText());


					Calculadora calc = new Calculadora(n1, n2);
					double resultado = 0;
					String operacion = "";

					if (tglbtnsumar.isSelected()) {
						resultado = calc.sumar();
						operacion = "Suma";
					} else if (tglbtnresta.isSelected()) {
						resultado = calc.restar();
						operacion = "Resta";
					} else if (tglbtnmultiplicacion.isSelected()) {
						resultado = calc.multiplicar();
						operacion = "Multiplicación";
					} else if (tglbtndividir.isSelected()) {
						resultado = calc.dividir();
						operacion = "División";
					} else if (tglbtnporcentaje.isSelected()) {
						resultado = calc.porcentaje();
						operacion = "Porcentaje";
					} else {
						txtS.setText("Por favor selecciona una operación.");
						return;
					}

					if (Math.abs(respuestaUsuario - resultado) < 0.0001) {
						txtS.setText("¡Felicidades! Respuesta correcta.\nOperación: " + operacion + "\nResultado: " + resultado);
					} else {
						txtS.setText("Respuesta incorrecta.\nOperación: " + operacion + "\nResultado correcto: " + resultado);
					}
				} catch (NumberFormatException ex) {
					txtS.setText("Error: ingresa números válidos.");
				}
			}
		});

		btnLimpiar.addActionListener(e -> {
			txtNumero1.setText("");
			txtNumero2.setText("");
			txtRespuesta.setText("");
			txtS.setText("");
			grupoOperaciones.clearSelection();
		});
	}
}
