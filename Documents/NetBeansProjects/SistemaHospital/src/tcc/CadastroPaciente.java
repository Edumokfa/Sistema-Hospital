
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Eduardo
 */
public class CadastroPaciente extends javax.swing.JInternalFrame implements ActionListener{

    /**
     * Creates new form CadastroPaciente
     */
    


    
    public CadastroPaciente() {
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
        BD.setResultSet("SELECT * FROM pacientes");
        
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
                    String sql = "SELECT * FROM pacientes WHERE CpfPaciente= '"+ ctCpf.getText() + "'";
                            BD.setResultSet(sql);
                            if (BD.resultSet.next()){
                                atualizaCampos();
                                ctNome.requestFocus();
                                setBotoes(true, false, true, true);
                            } else {
                                JOptionPane.showMessageDialog(null,"Cliente não encontrado!");
                                ctNome.requestFocus();
                                BD.setResultSet("SELECT * FROM pacientes");
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

            String sql = "";
            sql = "INSERT INTO pacientes(NomePaciente, EnderecoPaciente, BairroPaciente, CidadePaciente, RgPaciente, CpfPaciente, Sexopaciente) VALUES ( '"
                    + ctNome.getText() + "','"
                    + ctEndereco.getText() + "','"
                    + ctBairro.getText() + "','"
                    + ctCidade.getText() + "','"
                    + ctRg.getText() + "','"
                    + ctCpf.getText() + "','"
                    + cbSexo.getSelectedItem() + "')";
            System.out.println(sql);
            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso");
                setBotoes(true, false, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na inclusão, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM Pacientes");
        }
        
        if (e.getSource() == Alterar) {
            

            
            String sql = "";
            sql = "UPDATE pacientes SET "
                    + "NomePaciente ='" + ctNome.getText() + "',"
                    + "EnderecoPaciente ='" + ctEndereco.getText() + "',"
                    + "BairroPaciente ='" + ctBairro.getText() + "',"
                    + "CidadePaciente ='" + ctCidade.getText() + "',"
                    + "RgPaciente ='" + ctRg.getText() + "',"
                    + "CpfPaciente ='" + ctCpf.getText() + "'"
                    + "WHERE CpfPaciente ='" + ctCpf.getText() + "'";
            System.out.println(sql);

            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");
                setBotoes(true, false, false, true);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na alteração, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM pacientes");
        }

    }

    public void limpaCampos() {
        ctNome.setText("");
        ctEndereco.setText("");
        ctBairro.setText("");
        ctCidade.setText("");
        ctRg.setText("");
        ctCpf.setText("");
        cbSexo.setSelectedItem("Feminino");
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
            ctNome.setText(BD.resultSet.getString("NomePaciente"));
            ctEndereco.setText(BD.resultSet.getString("EnderecoPaciente"));
            ctBairro.setText(BD.resultSet.getString("BairroPaciente"));
            ctCidade.setText(BD.resultSet.getString("CidadePaciente"));
            ctCpf.setText(BD.resultSet.getString("CpfPaciente"));
            ctRg.setText(BD.resultSet.getString("RgPaciente"));
            cbSexo.setSelectedItem(BD.resultSet.getString("SexoPaciente"));
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
        ctRg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbSexo = new javax.swing.JComboBox<>();
        ctCpf = new javax.swing.JFormattedTextField();
        Cadastrar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        Localizar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadastrar Paciente");

        jLabel2.setText("Nome Completo");

        jLabel3.setText("Cidade");

        ctCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctCidadeActionPerformed(evt);
            }
        });

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

        Cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Salvar.png"))); // NOI18N
        Cadastrar.setText("Cadastrar");

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Novo.png"))); // NOI18N
        Novo.setText("Novo");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Alterar.png"))); // NOI18N
        Alterar.setText("Alterar");

        Localizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pesquisar.png"))); // NOI18N
        Localizar.setText("Localizar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(ctEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(158, 158, 158)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(271, 271, 271))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Localizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctNome)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(ctBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSexoActionPerformed

    private void ctCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctCidadeActionPerformed

    private void ctRgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ctRgKeyTyped
        if(ctRg.getText().length() >= 10){
            evt.consume();
        }
        
        
        
    }//GEN-LAST:event_ctRgKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Localizar;
    private javax.swing.JButton Novo;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JTextField ctBairro;
    private javax.swing.JTextField ctCidade;
    private javax.swing.JFormattedTextField ctCpf;
    private javax.swing.JTextField ctEndereco;
    private javax.swing.JTextField ctNome;
    private javax.swing.JTextField ctRg;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
