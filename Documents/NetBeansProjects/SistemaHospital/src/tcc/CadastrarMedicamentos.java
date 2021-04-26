
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Eduardo
 */
public class CadastrarMedicamentos extends javax.swing.JInternalFrame implements ActionListener{

    /**
     * Creates new form CadastrarMedicamentos
     */
    public CadastrarMedicamentos() {
     initComponents();
        
        Novo.addActionListener(this);
        Cadastrar.addActionListener(this);
        Alterar.addActionListener(this);
        Localizar.addActionListener(this);

        if (!BD.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado");
            System.exit(0);
        }
        BD.setResultSet("SELECT * FROM medicamentos");
        
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
                    String sql = "SELECT * FROM medicamentos WHERE idMedicamento= '"+ ctCodigo.getText()+"'";
                            BD.setResultSet(sql);
                            if (BD.resultSet.next()){
                                atualizaCampos();
                                ctCodigo.requestFocus();
                                setBotoes(true, false, true, true);
                            } else {
                                JOptionPane.showMessageDialog(null,"Medicamento não encontrado!");
                                ctCodigo.requestFocus();
                                BD.setResultSet("SELECT * FROM medicamentos");
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
            sql = "INSERT INTO medicamentos(idMedicamento, NomeComercial, NomeQuimico, ResumoMedicamento, UnidadeMedicamento, ClassificacaoFarmacologica, IntervaloDeTempo) VALUES ( '"
                    + ctCodigo.getText() + "','"
                    + ctNomeComercial.getText() + "','"
                    + ctNomeQuimico.getText() + "','"
                    + ctResumo.getText() + "','"
                    + cbUnidade.getSelectedItem() + "','"
                    + cbClassificacao.getSelectedItem() + "','"
                    + ctIntervaloDeTempo.getText() + "')";
            System.out.println(sql);
            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso");
                setBotoes(true, false, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na inclusão, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM medicamentos");
        }
        
        if (e.getSource() == Alterar) {
            
            
            String sql = "";
            sql = "UPDATE medicamentos SET "
                    + "idMedicamento ='" + ctCodigo.getText() + "',"
                    + "NomeComercial ='" + ctNomeComercial.getText() + "',"
                    + "NomeQuimico ='" + ctNomeQuimico.getText() + "',"
                    + "ResumoMedicamento ='" + ctResumo.getText() + "',"
                    + "UnidadeMedicamento ='" + cbUnidade.getSelectedIndex() + "',"
                    + "ClassificacaoFarmacologica ='" + cbClassificacao.getSelectedIndex() + "',"
                    + "IntervaloDeTempo ='" + ctIntervaloDeTempo.getText() + "'"
                    + "WHERE idMedicamento ='" + ctCodigo.getText() + "'";
            System.out.println(sql);

            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");
                setBotoes(true, false, false, true);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na alteração, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM medicamentos");
        }

    }

    public void limpaCampos() {
        ctCodigo.setText("");
        ctNomeComercial.setText("");
        ctNomeQuimico.setText("");
        ctResumo.setText("");
        ctIntervaloDeTempo.setText("");
        cbUnidade.setSelectedItem("Mg");
        cbClassificacao.setSelectedItem("Referência");
        ctCodigo.requestFocus();
    }
    
    public void atualizaCampos() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            ctCodigo.setText(BD.resultSet.getString("idMedicamento"));
            ctNomeComercial.setText(BD.resultSet.getString("NomeComercial"));
            ctNomeQuimico.setText(BD.resultSet.getString("NomeQuimico"));
            ctResumo.setText(BD.resultSet.getString("ResumoMedicamento"));
            ctIntervaloDeTempo.setText(BD.resultSet.getString("IntervaloDeTempo"));
            cbUnidade.setSelectedItem(BD.resultSet.getString("UnidadeMedicamento"));
            cbClassificacao.setSelectedItem(BD.resultSet.getString("ClassificacaoFarmacologica"));
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

        jLabel2 = new javax.swing.JLabel();
        ctNomeComercial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ctNomeQuimico = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbUnidade = new javax.swing.JComboBox<>();
        Cadastrar = new javax.swing.JButton();
        Localizar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ctResumo = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbClassificacao = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ctCodigo = new javax.swing.JTextField();
        ctIntervaloDeTempo = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Cadastrar Medicamentos");

        jLabel2.setText("Nome Comercial");

        ctNomeComercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctNomeComercialActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome Químico");

        ctNomeQuimico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctNomeQuimicoActionPerformed(evt);
            }
        });

        jLabel4.setText("Classificação Farmacologica");

        cbUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mg", "Ml", "Comprimido" }));

        Cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Salvar.png"))); // NOI18N
        Cadastrar.setText("Cadastrar");

        Localizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pesquisar.png"))); // NOI18N
        Localizar.setText("Localizar");

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Novo.png"))); // NOI18N
        Novo.setText("Novo");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Alterar.png"))); // NOI18N
        Alterar.setText("Alterar");

        ctResumo.setColumns(20);
        ctResumo.setRows(5);
        jScrollPane1.setViewportView(ctResumo);

        jLabel5.setText("Resumo / Informações adicionais");

        jLabel6.setText("Unidade");

        cbClassificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Referência", "Similar", "Genérico" }));

        jLabel7.setText("Código");

        ctCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctCodigoActionPerformed(evt);
            }
        });

        jLabel23.setText("Intervalo de tempo para ingerir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Localizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(ctCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ctNomeComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ctNomeQuimico)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbClassificacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ctIntervaloDeTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(153, 153, 153)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ctNomeComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctNomeQuimico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctIntervaloDeTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ctNomeComercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctNomeComercialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctNomeComercialActionPerformed

    private void ctNomeQuimicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctNomeQuimicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctNomeQuimicoActionPerformed

    private void ctCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctCodigoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Localizar;
    private javax.swing.JButton Novo;
    private javax.swing.JComboBox<String> cbClassificacao;
    private javax.swing.JComboBox<String> cbUnidade;
    private javax.swing.JTextField ctCodigo;
    private javax.swing.JTextField ctIntervaloDeTempo;
    private javax.swing.JTextField ctNomeComercial;
    private javax.swing.JTextField ctNomeQuimico;
    private javax.swing.JTextArea ctResumo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
