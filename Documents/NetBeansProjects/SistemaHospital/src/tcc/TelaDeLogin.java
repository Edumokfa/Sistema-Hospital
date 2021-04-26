/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static tcc.TelaDeCadastros.Conectado;
import static tcc.TelaDeCadastros.Pac;

public class TelaDeLogin extends javax.swing.JFrame {

    String senha;
    String loginBd;
    String senhaBd;
    
    static CadastroFuncionario enviaTexto;
    static CadastroMedico enviaTextoMedico;
    static Pacientes enviaTextoConectado;
    public static String Rfid;
    public static String Botao;
    public static String Conectado = "Sim";

    private String Login;

    public TelaDeLogin() {
        initComponents();

        if (!BD.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado");
            System.exit(0);
        }
        BD.setResultSet("SELECT * FROM funcionarios");
        BD.setResultSet("SELECT * FROM medicos");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoDeBotoes = new javax.swing.ButtonGroup();
        imagemDeFundo1 = new tcc.ImagemDeFundo();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        ctLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        ctSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Login");

        imagemDeFundo1.setImg(new ImageIcon("src/images/imagem_fundo.jpg"));

        GrupoDeBotoes.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Funcionário");

        GrupoDeBotoes.add(jRadioButton2);
        jRadioButton2.setText("Médico");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Bem Vindo à tela de Login");

        jLabel3.setText("Login");

        jLabel4.setText("Senha");

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(204, 0, 51));

        javax.swing.GroupLayout imagemDeFundo1Layout = new javax.swing.GroupLayout(imagemDeFundo1);
        imagemDeFundo1.setLayout(imagemDeFundo1Layout);
        imagemDeFundo1Layout.setHorizontalGroup(
            imagemDeFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagemDeFundo1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(imagemDeFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addGap(176, 176, 176))
            .addGroup(imagemDeFundo1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(imagemDeFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(imagemDeFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ctSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ctLogin)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, imagemDeFundo1Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        imagemDeFundo1Layout.setVerticalGroup(
            imagemDeFundo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagemDeFundo1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ctLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ctSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagemDeFundo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagemDeFundo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(ctSenha.getText().getBytes("UTF-8"));
            senha = messageDigest.toString();
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02X", 0xFF & b));
                senha = sb.toString();
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (jRadioButton1.isSelected()) {

            try {
                String sql = "SELECT * FROM funcionarios WHERE LoginFuncionario= '" + ctLogin.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    loginBd = BD.resultSet.getString("LoginFuncionario");
                    senhaBd = BD.resultSet.getString("SenhaFuncionario");
                    ctLogin.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
                    ctLogin.requestFocus();
                    BD.setResultSet("SELECT * FROM funcionarios");
                }
                if (loginBd.equals(ctLogin.getText()) && senha.equals(senhaBd)) {
                    TelaDeCadastros TelaPrincipal = new TelaDeCadastros();
                    TelaPrincipal.enviaUsuario(this, "Funcionario");

                    TelaPrincipal.setVisible(true);
                    this.setVisible(false);
                } else {
                    jLabel5.setText("A senha está incorreta");
                }
            } catch (Exception erro) {

            }

        } else if (jRadioButton2.isSelected()) {
            try {
                String sql = "SELECT * FROM medicos WHERE LoginMedico= '" + ctLogin.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    loginBd = BD.resultSet.getString("LoginMedico");
                    senhaBd = BD.resultSet.getString("SenhaMedico");
                    ctLogin.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
                    ctLogin.requestFocus();
                    BD.setResultSet("SELECT * FROM medicos");
                }
                if (loginBd.equals(ctLogin.getText()) && senha.equals(senhaBd)) {
                    TelaDeCadastros TelaPrincipal = new TelaDeCadastros();
                    TelaPrincipal.enviaUsuario(this, "Medico");
                    TelaPrincipal.setVisible(true);

                    this.setVisible(false);
                } else {
                    jLabel5.setText("A senha está incorreta");
                }
            } catch (Exception erro) {

            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDeLogin().setVisible(true);
            }
        });
        
        System.out.println("Servidor Iniciado");
        ServerSocket listener = new ServerSocket(3090);

        try {
            while (true) {
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                System.out.println("Client Connected");
                
                Pac.enviaTextoConectado(Conectado);
                Conectado = "";
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String Rfid2 = (in.readLine());
                    System.out.println(Rfid2);

                    if (Rfid2.length() == 8) {
                        Rfid = Rfid2;
                        Pac.enviaTextoPressionado(Rfid);
                    } else if (Rfid2.length() == 5) {
                        Botao = "pressionado";
                        Pac.enviaTextoPressionado(Botao);
                    }

                    if (enviaTexto == null) {
                        enviaTexto = new CadastroFuncionario();
                        enviaTextoMedico = new CadastroMedico();

                        enviaTextoMedico.recebe(Rfid);

                    } else {
                        enviaTexto.recebe(Rfid);
                        enviaTextoMedico.recebe(Rfid);

                    }

                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoDeBotoes;
    private javax.swing.JTextField ctLogin;
    private javax.swing.JPasswordField ctSenha;
    private tcc.ImagemDeFundo imagemDeFundo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the Login
     */
    public String getLogin() {
        return Login;
    }


    public void setLogin(String Login) {
        this.Login = Login;
    }
}
