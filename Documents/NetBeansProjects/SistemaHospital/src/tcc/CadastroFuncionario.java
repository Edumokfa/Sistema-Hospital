/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 *
 * @author Eduardo
 */
public class CadastroFuncionario extends javax.swing.JInternalFrame implements ActionListener{   
    
    
    
    private static String cpf;
    private static String Rfid;
    String senha;
    public CadastroFuncionario() {   
        
        initComponents();
        FormatarCampoCpf();
        
        Novo.addActionListener(this);
        Cadastrar.addActionListener(this);
        Alterar.addActionListener(this);
        Localizar.addActionListener(this);
        ctCpf.addFocusListener(new PosicaoCursor());
        ctCpf.addFocusListener(new PosicaoCursor());
        
        if (!BD.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado");
            System.exit(0);
        }
        BD.setResultSet("SELECT * FROM funcionarios");
              
        
    }
    
    private void FormatarCampoCpf(){
        try{
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.install(ctCpf);
        }catch (ParseException ex){
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto.", "ERRO", JOptionPane.ERROR);
        }
    }

    
    class PosicaoCursor extends FocusAdapter{
    public void focusGained(FocusEvent e) {

        JTextComponent comp = (JTextComponent) e.getSource();           
        comp.setCaretPosition(0);
    }       
} 
  
    
     public void actionPerformed(ActionEvent e) {
         
     
        if (e.getSource() == Localizar){
            try{
                    String sql = "SELECT * FROM funcionarios WHERE CpfFuncionario= '"+ ctCpf.getText()+"'";
                            BD.setResultSet(sql);
                            if (BD.resultSet.next()){
                                atualizaCampos();
                                ctNome.requestFocus();
                                setBotoes(true, false, true, true);
                            } else {
                                JOptionPane.showMessageDialog(null,"Funcionário não encontrado!");
                                ctNome.requestFocus();
                                BD.setResultSet("SELECT * FROM funcionarios");
                            }
                }catch(Exception erro){
                    
                }
        }
         
         if (e.getSource() == Novo) {
            setBotoes(true, true, false, true);
            limpaCampos();
            return;
        }

        if (e.getSource() == Cadastrar) {
           
;
            
            try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(ctSenha.getText().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
        for(byte b: messageDigest){
            sb.append(String.format("%02X", 0xFF & b));
            senha = sb.toString();
        }
            String sql = "";
            sql = "INSERT INTO funcionarios(NomeFuncionario, EnderecoFuncionario, BairroFuncionario, CidadeFuncionario, RfidFuncionario, RgFuncionario, CpfFuncionario, AreaAtuacaoFuncionario, FormacaoFuncionario,LoginFuncionario,SenhaFuncionario, SexoFuncionario) VALUES ("
                    +"'"
                    + ctNome.getText() + "','"
                    + ctEndereco.getText() + "','"
                    + ctBairro.getText() + "','"
                    + ctCidade.getText() + "','"
                    + ctRfid.getText() + "','"
                    + ctRg.getText() + "','"
                    + ctCpf.getText() + "','"
                    + ctAtuacao.getText() + "','"
                    + ctFormacao.getText() + "','"
                    + ctLogin.getText() + "','"
                    + senha + "','"
                    + cbSexo.getSelectedItem() + "')";
            System.out.println(sql);
            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso");
                setBotoes(true, false, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na inclusão, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM funcionarios");
            }catch(NoSuchAlgorithmException ex){
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex); 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if (e.getSource() == Alterar) {
            try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(ctSenha.getText().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte b: messageDigest){
            sb.append(String.format("%02X", 0xFF & b));
            senha = sb.toString();
        }
            String sql = "";
            sql = "UPDATE funcionarios SET "
                    + "NomeFuncionario ='" + ctNome.getText() + "',"
                    + "EnderecoFuncionario ='" + ctEndereco.getText() + "',"
                    + "BairroFuncionario ='" + ctBairro.getText() + "',"
                    + "CidadeFuncionario ='" + ctCidade.getText() + "',"
                    + "RfidFuncionario ='" + ctRfid.getText() + "',"
                    + "RgFuncionario ='" + ctRg.getText() + "',"
                    + "CpfFuncionario ='" + ctCpf.getText() + "',"
                    + "AreaatuacaoFuncionario ='" + ctAtuacao.getText() + "',"
                    + "FormacaoFuncionario ='" + ctFormacao.getText() + "',"
                    + "LoginFuncionario ='" + ctLogin.getText() + "',"
                    + "SenhaFuncionario ='" + senha + "',"
                    + "SexoFuncionario ='" + cbSexo.getSelectedItem() + "'"
                    + "WHERE CpfFuncionario ='" + ctCpf.getText() + "'";
            System.out.println(sql);

            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");
                setBotoes(true, false, false, true);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na alteração, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM funcionarios");
        }catch(NoSuchAlgorithmException ex){
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex); 
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void limpaCampos() {
        ctNome.setText("");
        ctEndereco.setText("");
        ctBairro.setText("");
        ctCidade.setText("");
        ctRg.setText("");
        ctCpf.setText("");
        ctAtuacao.setText("");
        ctFormacao.setText("");
        ctRfid.setText("");
        cbSexo.setSelectedItem("Feminino");
        ctNome.requestFocus();
        ctLogin.setText("");
        ctSenha.setText("");
        
               
    }
    
    public void atualizaCampos() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }

            
            ctNome.setText(BD.resultSet.getString("NomeFuncionario"));
            ctEndereco.setText(BD.resultSet.getString("EnderecoFuncionario"));
            ctBairro.setText(BD.resultSet.getString("BairroFuncionario"));
            ctCidade.setText(BD.resultSet.getString("CidadeFuncionario"));
            ctCpf.setText(BD.resultSet.getString("CpfFuncionario"));
            ctRg.setText(BD.resultSet.getString("RgFuncionario"));
            ctRfid.setText(BD.resultSet.getString("RfidFuncionario"));
            ctAtuacao.setText(BD.resultSet.getString("AreaatuacaoFuncionario"));
            ctFormacao.setText(BD.resultSet.getString("FormacaoFuncionario"));
            cbSexo.setSelectedItem(BD.resultSet.getString("SexoFuncionario"));
            ctLogin.setText(BD.resultSet.getString("LoginFuncionario"));
            ctSenha.setText("");
            
        } catch (SQLException erro) {

        }
            
    }

    public void setBotoes(boolean bNovo, boolean bGravar, boolean bAlterar, boolean bLocalizar) {
        Novo.setEnabled(bNovo);
        Cadastrar.setEnabled(bGravar);
        Alterar.setEnabled(bAlterar);
        Localizar.setEnabled(bLocalizar);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ctNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ctCidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ctBairro = new javax.swing.JTextField();
        ctEndereco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ctFormacao = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        ctAtuacao = new javax.swing.JTextField();
        ctRg = new javax.swing.JFormattedTextField();
        Cadastrar = new javax.swing.JButton();
        Localizar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        ctCpf = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        ctRfid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ctLogin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ctSenha = new javax.swing.JTextField();
        Excluir = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadastrar Funcionário");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel2.setText("Nome Completo");

        jLabel3.setText("Cidade");

        jLabel4.setText("Bairro");

        jLabel5.setText("Endereço");

        jLabel7.setText("RG");

        jLabel8.setText("CPF");

        jLabel9.setText("Sexo");

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Feminino", "Masculino" }));
        cbSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSexoActionPerformed(evt);
            }
        });

        jLabel10.setText("Formação");

        ctFormacao.setColumns(20);
        ctFormacao.setRows(5);
        jScrollPane1.setViewportView(ctFormacao);

        jLabel11.setText("Área de atuação");

        ctRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctRgKeyTyped(evt);
            }
        });

        Cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Salvar.png"))); // NOI18N
        Cadastrar.setText("Cadastrar");
        Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarActionPerformed(evt);
            }
        });

        Localizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pesquisar.png"))); // NOI18N
        Localizar.setText("Localizar");

        Novo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\Novo.png")); // NOI18N
        Novo.setText("Novo");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Alterar.png"))); // NOI18N
        Alterar.setText("Alterar");

        jLabel13.setText("RFID");

        ctRfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ctRfidKeyPressed(evt);
            }
        });

        jLabel12.setText("Login");

        jLabel14.setText("Senha");

        Excluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\excluir.png")); // NOI18N
        Excluir.setText("Excluir");
        Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ctAtuacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(ctRfid, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(ctNome, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ctEndereco)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(207, 207, 207)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ctLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ctSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctAtuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctRfid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarActionPerformed

    }//GEN-LAST:event_CadastrarActionPerformed

    private void ctRgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctRgKeyTyped
        if(ctRg.getText().length() >= 10){
            evt.consume();
        }
    }//GEN-LAST:event_ctRgKeyTyped

    private void cbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSexoActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

    private void ctRfidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctRfidKeyPressed
        String teste = "teste";
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(Rfid != teste){
        ctRfid.setText(Rfid);
            System.out.println(Rfid);
            }
        }
        
    }//GEN-LAST:event_ctRfidKeyPressed
public static void retornoJoptionPane() {
        int i = JOptionPane.showConfirmDialog(null, "Você tem certeza?", "AVISO!", 0);
        if (i == 0) {
             String sql = "DELETE FROM funcionarios WHERE CpfFuncionario = '" + cpf + "'" ;
                    int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso");
               
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na exclusão");
            }
        } else if (i == 1) {
            
        }

    }
    private void ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirActionPerformed
         cpf = ctCpf.getText();
        retornoJoptionPane();
    }//GEN-LAST:event_ExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Excluir;
    private javax.swing.JButton Localizar;
    private javax.swing.JButton Novo;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JTextField ctAtuacao;
    private javax.swing.JTextField ctBairro;
    private javax.swing.JTextField ctCidade;
    private javax.swing.JFormattedTextField ctCpf;
    private javax.swing.JTextField ctEndereco;
    private javax.swing.JTextArea ctFormacao;
    private javax.swing.JTextField ctLogin;
    private javax.swing.JTextField ctNome;
    private javax.swing.JTextField ctRfid;
    private javax.swing.JFormattedTextField ctRg;
    private javax.swing.JTextField ctSenha;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the Rfid
     */
    public String getRfid() {
        return Rfid;
    }

    /**
     * @param Rfid the Rfid to set
     */
    public void setRfid(String Rfid) {
        this.Rfid = Rfid;
    }

    
    public void recebe(String recebe){
        Rfid = recebe;
    }

}