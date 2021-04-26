
package tcc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;


public class CadastroQuartos extends javax.swing.JInternalFrame implements ActionListener{

    
    public CadastroQuartos() {
             initComponents();
        
        Novo.addActionListener(this);
        Cadastrar.addActionListener(this);
        Alterar.addActionListener(this);
        Localizar.addActionListener(this);

        if (!BD.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado");
            System.exit(0);
        }
        BD.setResultSet("SELECT * FROM quarto");
        
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
                    String sql = "SELECT * FROM quarto WHERE Numero= "+ ctNumero.getText();
                            BD.setResultSet(sql);
                            if (BD.resultSet.next()){
                                atualizaCampos();
                                ctNumero.requestFocus();
                                setBotoes(true, false, true, true);
                            } else {
                                JOptionPane.showMessageDialog(null,"Quarto não encontrado!");
                                ctNumero.requestFocus();
                                BD.setResultSet("SELECT * FROM quarto");
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
            sql = "INSERT INTO quarto(Numero, PavimentoQuarto, FuncaoQuarto) VALUES ( '"
                    + ctNumero.getText() + "','"
                    + ctPavimento.getText() + "','"
                    + cbFuncao.getSelectedItem() + "')";
            System.out.println(sql);
            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso");
                setBotoes(true, false, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na inclusão, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM quarto");
        }
        
        if (e.getSource() == Alterar) {
            
            
            String sql = "";
            sql = "UPDATE quarto SET "
                    + "Numero ='" + ctNumero.getText() + "',"
                    + "FuncaoQuarto ='" + cbFuncao.getSelectedItem() + "',"
                    + "PavimentoQuarto ='" + ctPavimento.getText() + "'"
                    + "WHERE Numero ='" + ctNumero.getText() + "'";
            System.out.println(sql);

            int r = BD.runSQL(sql);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso");
                setBotoes(true, false, false, true);
            } else {
                JOptionPane.showMessageDialog(null, "Problemas na alteração, verifique se você digitou os campos corretamente");
            }
            BD.setResultSet("SELECT * FROM quarto");
        }

    }

    public void limpaCampos() {
        ctNumero.setText("");
        ctPavimento.setText("");
        cbFuncao.setSelectedItem("Cirurgia");
        ctNumero.requestFocus();
    }
    
    public void atualizaCampos() {
        try {
            if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            ctNumero.setText(BD.resultSet.getString("Numero"));
            ctPavimento.setText(BD.resultSet.getString("PavimentoQuarto"));
            cbFuncao.setSelectedItem(BD.resultSet.getString("FuncaoQuarto"));
        } catch (SQLException erro) {

        }
    }

    public void setBotoes(boolean bNovo, boolean bGravar, boolean bAlterar, boolean bLocalizar) {
        Novo.setEnabled(bNovo);
        Cadastrar.setEnabled(bGravar);
        Alterar.setEnabled(bAlterar);
        Localizar.setEnabled(bLocalizar);
    }

    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ctNumero = new javax.swing.JTextField();
        ctPavimento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Cadastrar = new javax.swing.JButton();
        Localizar = new javax.swing.JButton();
        Novo = new javax.swing.JButton();
        Alterar = new javax.swing.JButton();
        cbFuncao = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Cadastrar Quartos");

        ctNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctNumeroActionPerformed(evt);
            }
        });

        ctPavimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctPavimentoActionPerformed(evt);
            }
        });

        jLabel1.setText("Número");

        jLabel2.setText("Função");

        jLabel3.setText("Pavimento");

        Cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Salvar.png"))); // NOI18N
        Cadastrar.setText("Cadastrar");

        Localizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pesquisar.png"))); // NOI18N
        Localizar.setText("Localizar");

        Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Novo.png"))); // NOI18N
        Novo.setText("Novo");

        Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Alterar.png"))); // NOI18N
        Alterar.setText("Alterar");

        cbFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cirurgia", "Repouso", "Espera" }));
        cbFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFuncaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Localizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(ctNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Alterar, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ctPavimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Localizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ctPavimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctPavimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctPavimentoActionPerformed

    private void cbFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuncaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFuncaoActionPerformed

    private void ctNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ctNumeroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Alterar;
    private javax.swing.JButton Cadastrar;
    private javax.swing.JButton Localizar;
    private javax.swing.JButton Novo;
    private javax.swing.JComboBox<String> cbFuncao;
    private javax.swing.JTextField ctNumero;
    private javax.swing.JTextField ctPavimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
