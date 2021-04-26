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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Eduardo
 */
public class CadastroMedico extends javax.swing.JInternalFrame implements ActionListener {

    private static String Rfid;
    String senha;
    private static String cpf;
    /**
     * Creates new form CadastroMedico
     */
    public CadastroMedico() {
        initComponents();

        FormatarCampoCpf();

        Novo.addActionListener(this);
        Cadastrar.addActionListener(this);
        Alterar.addActionListener(this);
        Localizar.addActionListener(this);

        if (!BD.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado");
            System.exit(0);
        }
        BD.setResultSet("SELECT * FROM medicos");
    }

    private void FormatarCampoCpf() {
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.install(ctCpf);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto.", "ERRO", JOptionPane.ERROR);
        }
    }

    class PosicaoCursor extends FocusAdapter {

        public void focusGained(FocusEvent e) {

            JTextComponent comp = (JTextComponent) e.getSource();
            comp.setCaretPosition(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Localizar) {
            try {
                String sql = "SELECT * FROM medicos WHERE CpfMedico= '" + ctCpf.getText() + "'";
                BD.setResultSet(sql);
                if (BD.resultSet.next()) {
                    atualizaCampos();
                    ctNome.requestFocus();
                    setBotoes(true, false, true, true);
                } else {
                    JOptionPane.showMessageDialog(null, "Médico não encontrado!");
                    ctNome.requestFocus();
                    BD.setResultSet("SELECT * FROM medicos");
                }
            } catch (Exception erro) {

            }
        }

        if (e.getSource() == Novo) {
            setBotoes(true, true, false, true);
            limpaCampos();
            return;
        }

        if (e.getSource() == Cadastrar) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = md.digest(ctSenha.getText().getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (byte b : messageDigest) {
                    sb.append(String.format("%02X", 0xFF & b));
                    senha = sb.toString();
                }

                String sql = "";
                sql = "INSERT INTO medicos(NomeMedico, EnderecoMedico, BairroMedico, CidadeMedico, RgMedico, CpfMedico, AreaAtuacaoMedico, SexoMedico, CrmMedico, LoginMedico, SenhaMedico, RfidMedico, FormacaoMedico) VALUES ('"
                        + ctNome.getText() + "','"
                        + ctEndereco.getText() + "','"
                        + ctBairro.getText() + "','"
                        + ctCidade.getText() + "','"
                        + ctRg.getText() + "','"
                        + ctCpf.getText() + "','"
                        + ctAtuacao.getText() + "','"
                        + cbSexo.getSelectedItem() + "','"
                        + ctCrm.getText() + "','"
                        + ctLogin.getText() + "','"
                        + senha + "','"
                        + ctRfid.getText() + "','"
                        + ctFormacao.getText() + "')";
                System.out.println(sql);
                int r = BD.runSQL(sql);

                if (r == 1) {
                    JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso");
                    setBotoes(true, false, true, false);
                } else {
                    JOptionPane.showMessageDialog(null, "Problemas na inclusão, verifique se você digitou os campos corretamente");
                }
                BD.setResultSet("SELECT * FROM medicos");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == Alterar) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = md.digest(ctSenha.getText().getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (byte b : messageDigest) {
                    sb.append(String.format("%02X", 0xFF & b));
                    senha = sb.toString();
                }

                String sql = "";
                sql = "UPDATE medicos SET "
                        + "NomeMedico ='" + ctNome.getText() + "',"
                        + "EnderecoMedico ='" + ctEndereco.getText() + "',"
                        + "BairroMedico ='" + ctBairro.getText() + "',"
                        + "CidadeMedico ='" + ctCidade.getText() + "',"
                        + "RgMedico ='" + ctRg.getText() + "',"
                        + "CpfMedico ='" + ctCpf.getText() + "',"
                        + "AreaAtuacaoMedico ='" + ctAtuacao.getText() + "',"
                        + "FormacaoMedico ='" + ctFormacao.getText() + "',"
                        + "CrmMedico ='" + ctCrm.getText() + "',"
                        + "SexoMedico ='" + cbSexo.getSelectedItem() + "',"
                        + "RfidMedico ='" + cbSexo.getSelectedItem() + "'"
                        + "LoginMedico ='" + cbSexo.getSelectedItem() + "'"
                        + "SenhaMedico ='" + senha + "'"
                        + "WHERE CpfMedico ='" + ctCpf.getText() + "'";
                System.out.println(sql);

                int r = BD.runSQL(sql);
                if (r == 1) {
                    JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");
                    setBotoes(true, false, false, true);
                } else {
                    JOptionPane.showMessageDialog(null, "Problemas na alteração, verifique se você digitou os campos corretamente");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CadastroMedico.class.getName()).log(Level.SEVERE, null, ex);
            }
            BD.setResultSet("SELECT * FROM medicos");
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
        ctCrm.setText("");
        cbSexo.setSelectedItem("Feminino");
        ctLogin.setText("");
        ctSenha.setText("");
        ctRfid.setText("");
        ctNome.requestFocus();
    }

    public void atualizaCampos() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }

            ctCrm.setText(BD.resultSet.getString("CrmMedico"));
            ctNome.setText(BD.resultSet.getString("NomeMedico"));
            ctEndereco.setText(BD.resultSet.getString("EnderecoMedico"));
            ctBairro.setText(BD.resultSet.getString("BairroMedico"));
            ctCidade.setText(BD.resultSet.getString("CidadeMedico"));
            ctCpf.setText(BD.resultSet.getString("CpfMedico"));
            ctRg.setText(BD.resultSet.getString("RgMedico"));
            ctAtuacao.setText(BD.resultSet.getString("AreaAtuacaoMedico"));
            ctFormacao.setText(BD.resultSet.getString("FormacaoMedico"));
            cbSexo.setSelectedItem(BD.resultSet.getString("SexoMedico"));
            ctLogin.setText(BD.resultSet.getString("LoginMedico"));
            ctSenha.setText("");
            ctRfid.setText(BD.resultSet.getString("RfidMedico"));

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
        Cadastrar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        Localizar = new javax.swing.JButton();
        ctLogin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ctSenha = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Excluir = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        ctAtuacao = new javax.swing.JTextField();
        ctCpf = new javax.swing.JFormattedTextField();
        ctCrm = new javax.swing.JTextField();
        ctNome = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ctCidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ctBairro = new javax.swing.JTextField();
        ctEndereco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ctRg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ctFormacao = new javax.swing.JTextArea();
        ctRfid = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Cadastrar Médico");

        Cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Salvar.png"))); // NOI18N
        Cadastrar.setText("Cadastrar");

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Novo.png"))); // NOI18N
        Novo.setText("Novo");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Alterar.png"))); // NOI18N
        Alterar.setText("Alterar");

        Localizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pesquisar.png"))); // NOI18N
        Localizar.setText("Localizar");

        jLabel14.setText("Login");

        jLabel15.setText("Senha");

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ctLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ctSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("Área de atuação");

        ctCrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctCrmActionPerformed(evt);
            }
        });
        ctCrm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctCrmKeyTyped(evt);
            }
        });

        jLabel12.setText("CRM");

        jLabel2.setText("Nome Completo");

        jLabel3.setText("Cidade");

        jLabel4.setText("Bairro");

        jLabel5.setText("Rua, Número");

        jLabel7.setText("RG");

        ctRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ctRgKeyTyped(evt);
            }
        });

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

        ctRfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ctRfidKeyPressed(evt);
            }
        });

        jLabel17.setText("RFID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(ctAtuacao)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(ctNome, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(ctBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(ctEndereco)))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ctCrm, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(ctRfid, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctAtuacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctCrm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctRfid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSexoActionPerformed

    private void ctRgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctRgKeyTyped
        if (ctRg.getText().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_ctRgKeyTyped

    private void ctRfidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctRfidKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ctRfid.setText(Rfid);
        }
    }//GEN-LAST:event_ctRfidKeyPressed

    private void ctCrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctCrmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctCrmActionPerformed

    private void ctCrmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctCrmKeyTyped
        if (ctCrm.getText().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_ctCrmKeyTyped
    
    public static void retornoJoptionPane() {
        int i = JOptionPane.showConfirmDialog(null, "Você tem certeza?", "AVISO!", 0);
        if (i == 0) {
             String sql = "DELETE FROM medicos WHERE CpfMedico = '" + cpf + "'" ;
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
        /*
        
         */

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
    private javax.swing.JTextField ctCrm;
    private javax.swing.JTextField ctEndereco;
    private javax.swing.JTextArea ctFormacao;
    private javax.swing.JTextField ctLogin;
    private javax.swing.JTextField ctNome;
    private javax.swing.JTextField ctRfid;
    private javax.swing.JTextField ctRg;
    private javax.swing.JTextField ctSenha;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
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
public void recebe(String recebe) {
        Rfid = recebe;
    }

}
