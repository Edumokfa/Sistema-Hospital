package tcc;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaDeCadastros extends javax.swing.JFrame {

    static CadastroFuncionario enviaTexto;
    static CadastroMedico enviaTextoMedico;
    static Pacientes enviaTextoConectado;
    private String Login;

    static Pacientes Pac = new Pacientes();

    public static String Rfid;
    public static String Botao;
    public static String Conectado;

    String EnviaRfid = "";

    public TelaDeCadastros() {
        initComponents();
        
        this.setExtendedState(MAXIMIZED_BOTH);
        
    }

    CadastroMedico CadMed = new CadastroMedico();
    CadastroFuncionario CadFun = new CadastroFuncionario();
    CadastroPaciente CadPac = new CadastroPaciente();
    CadastroQuartos CadQuartos = new CadastroQuartos();
    CadastrarMedicamentos CadMedicamentos = new CadastrarMedicamentos();
    CadastroProntuario CadProntuario = new CadastroProntuario();

    public void enviaUsuario(TelaDeLogin veioDoLogin, String nome) {
        if (nome.equals("Funcionario")) {
            jMenuItem5.setEnabled(false);
            Medicamento.setEnabled(false);
        } else {
            Medico.setEnabled(false);
            Funcionario.setEnabled(false);
            Paciente.setEnabled(false);
            Quarto.setEnabled(false);
                    
            jMenuItem5.setEnabled(true);
            jMenuItem1.setEnabled(false);
            jMenuItem2.setEnabled(false);
            jMenuItem3.setEnabled(false);
            jMenuItem4.setEnabled(false);
        }
    }

    private void centralizaForm(JInternalFrame form) {
        Dimension desktopSize = TelaCadastro.getSize();
        Dimension jInternalFrameSize = form.getSize();
        form.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TelaCadastro = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Medicamento = new javax.swing.JButton();
        Funcionario = new javax.swing.JButton();
        Paciente = new javax.swing.JButton();
        Medico = new javax.swing.JButton();
        Prontuario = new javax.swing.JButton();
        Quarto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Quartos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Prontuario1 = new javax.swing.JButton();
        Prontuario2 = new javax.swing.JButton();
        Prontuario3 = new javax.swing.JButton();
        Prontuario4 = new javax.swing.JButton();
        Prontuario5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TelaCadastro.setBackground(new java.awt.Color(204, 204, 204));
        TelaCadastro.setToolTipText("");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("CADASTRAR");

        Medicamento.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\Medicamento.png")); // NOI18N
        Medicamento.setText("Medicamento");
        Medicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MedicamentoActionPerformed(evt);
            }
        });

        Funcionario.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\funcionario.png")); // NOI18N
        Funcionario.setText("Funcionário");
        Funcionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuncionarioActionPerformed(evt);
            }
        });

        Paciente.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\paciente.png")); // NOI18N
        Paciente.setText("Paciente");
        Paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PacienteActionPerformed(evt);
            }
        });

        Medico.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\Medico.png")); // NOI18N
        Medico.setText("Médico");
        Medico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MedicoActionPerformed(evt);
            }
        });

        Prontuario.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\Prontuario.png")); // NOI18N
        Prontuario.setText("Prontuário");
        Prontuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProntuarioActionPerformed(evt);
            }
        });

        Quarto.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\quarto.png")); // NOI18N
        Quarto.setText("Quarto");
        Quarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuartoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Prontuario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(Medico, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(Funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(Paciente, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(Quarto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(Medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Paciente, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Medico, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Prontuario, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Quarto, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Abrir tela de quartos");

        Quartos.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\quartos.png")); // NOI18N
        Quartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuartosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Quartos, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(Quartos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Gerar Relatórios");

        Prontuario1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\document_report_16751.png")); // NOI18N
        Prontuario1.setText("Relatório de atendimentos");
        Prontuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prontuario1ActionPerformed(evt);
            }
        });

        Prontuario2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\document_report_16751.png")); // NOI18N
        Prontuario2.setText("Relatório de Médicos");
        Prontuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prontuario2ActionPerformed(evt);
            }
        });

        Prontuario3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\document_report_16751.png")); // NOI18N
        Prontuario3.setText("Relatório de Prontuários");
        Prontuario3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prontuario3ActionPerformed(evt);
            }
        });

        Prontuario4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\document_report_16751.png")); // NOI18N
        Prontuario4.setText("Relatório de pacientes");
        Prontuario4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prontuario4ActionPerformed(evt);
            }
        });

        Prontuario5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\document_report_16751.png")); // NOI18N
        Prontuario5.setText("Relatório de Funcionários");
        Prontuario5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prontuario5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Prontuario1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                            .addComponent(Prontuario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Prontuario3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Prontuario4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(Prontuario5, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Prontuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Prontuario5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Prontuario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Prontuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Prontuario4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Eduardo\\Documents\\NetBeansProjects\\TCC\\Icons\\hospital.png")); // NOI18N
        jLabel4.setText("HOSPITAL TESTE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(518, 518, 518)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        TelaCadastro.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TelaCadastro.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TelaCadastro.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        TelaCadastro.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TelaCadastroLayout = new javax.swing.GroupLayout(TelaCadastro);
        TelaCadastro.setLayout(TelaCadastroLayout);
        TelaCadastroLayout.setHorizontalGroup(
            TelaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TelaCadastroLayout.setVerticalGroup(
            TelaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TelaCadastroLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setToolTipText("");

        jMenu1.setText("Cadastrar");

        jMenuItem6.setText("Prontuário");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem1.setText("Funcionário");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Médico");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Paciente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Quartos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Medicamentos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Emitir");

        jMenuItem8.setText("Relatório de atendimentos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Relatório de prontuários");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem11.setText("Relatório de Médicos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Relatório de Funcionários");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setText("Relatório de Pacientes");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Pacientes");

        jMenuItem7.setText("Quartos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Informações");

        jMenuItem10.setText("Ajuda");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaCadastro)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TelaCadastro)
        );

        TelaCadastro.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        centralizaForm(CadMed);
        TelaCadastro.add(CadMed);
        CadMed.setVisible(true);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        centralizaForm(CadFun);
        TelaCadastro.add(CadFun);
        CadMed.setVisible(false);
        CadFun.setVisible(true);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        centralizaForm(CadPac);
        TelaCadastro.add(CadPac);
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(true);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        centralizaForm(CadQuartos);
        TelaCadastro.add(CadQuartos);
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(true);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        centralizaForm(CadMedicamentos);
        TelaCadastro.add(CadMedicamentos);
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(true);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
        
        
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        
        
        
        String sql = "";
            sql = "Select Max(idProntuario) from prontuario";
            System.out.println(sql);
            BD.setResultSet(sql);
            try {
                if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            
            
            
            centralizaForm(CadProntuario);
            TelaCadastro.add(CadProntuario);
            CadProntuario.setVisible(true);
            int IdMax;
            if(BD.resultSet.getString("Max(idProntuario)") == null){
                IdMax = 0;
            }else{
                IdMax = (Integer.parseInt(BD.resultSet.getString("Max(idProntuario)")));
            }
            CadastroProntuario cp = new CadastroProntuario();
            
            cp.recebe(IdMax);
            }catch(SQLException erro){
                
                }
        
        
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        if (Pac == null) {
            Pac = new Pacientes();
            centralizaForm(Pac);
            TelaCadastro.add(Pac);
            Pac.setVisible(true);
        } else {
            centralizaForm(Pac);
            TelaCadastro.add(Pac);
            Pac.setVisible(true);
        }
        

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed

        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioAtendimento.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }


    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioProntuarios.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        File pdf = new File("Ajuda do Sistema.pdf");
        try {
            Desktop.getDesktop().open(pdf);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao abrir documento: " + ex);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void ProntuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProntuarioActionPerformed
           
        
        
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        
        
        
        String sql = "";
            sql = "Select Max(idProntuario) from prontuario";
            System.out.println(sql);
            BD.setResultSet(sql);
            try {
                if (BD.resultSet.isAfterLast()) {
                BD.resultSet.last();
            }
            if (BD.resultSet.isBeforeFirst()) {
                BD.resultSet.first();
            }
            
            
            
            centralizaForm(CadProntuario);
            TelaCadastro.add(CadProntuario);
            CadProntuario.setVisible(true);
            int IdMax;
            if(BD.resultSet.getString("Max(idProntuario)") == null){
                IdMax = 0;
            }else{
                IdMax = (Integer.parseInt(BD.resultSet.getString("Max(idProntuario)")));
            }
            CadastroProntuario cp = new CadastroProntuario();
            
            cp.recebe(IdMax);
            }catch(SQLException erro){
                
                }
    }//GEN-LAST:event_ProntuarioActionPerformed

    private void FuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuncionarioActionPerformed
        centralizaForm(CadFun);
        TelaCadastro.add(CadFun);
        CadMed.setVisible(false);
        CadFun.setVisible(true);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_FuncionarioActionPerformed

    private void MedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MedicoActionPerformed
        centralizaForm(CadMed);
        TelaCadastro.add(CadMed);
        CadMed.setVisible(true);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_MedicoActionPerformed

    private void PacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PacienteActionPerformed
        centralizaForm(CadPac);
        TelaCadastro.add(CadPac);
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(true);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_PacienteActionPerformed

    private void QuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuartoActionPerformed
        centralizaForm(CadQuartos);
        TelaCadastro.add(CadQuartos);
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(true);
        CadMedicamentos.setVisible(false);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_QuartoActionPerformed

    private void MedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MedicamentoActionPerformed
        centralizaForm(CadMedicamentos);
        TelaCadastro.add(CadMedicamentos);
        CadMed.setVisible(false);
        CadFun.setVisible(false);
        CadPac.setVisible(false);
        CadQuartos.setVisible(false);
        CadMedicamentos.setVisible(true);
        CadProntuario.setVisible(false);
    }//GEN-LAST:event_MedicamentoActionPerformed

    private void QuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuartosActionPerformed
        if (Pac == null) {
            Pac = new Pacientes();
            centralizaForm(Pac);
            TelaCadastro.add(Pac);
            Pac.setVisible(true);
        } else {
            centralizaForm(Pac);
            TelaCadastro.add(Pac);
            Pac.setVisible(true);
        }
    }//GEN-LAST:event_QuartosActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        
         final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioMedicos.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
        
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
         final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioFuncionarios.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
         final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "Pacientes.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void Prontuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prontuario1ActionPerformed
        
        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioAtendimento.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }

    }//GEN-LAST:event_Prontuario1ActionPerformed

    private void Prontuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prontuario2ActionPerformed
        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioMedicos.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
    }//GEN-LAST:event_Prontuario2ActionPerformed

    private void Prontuario3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prontuario3ActionPerformed
         final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioProntuarios.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
    }//GEN-LAST:event_Prontuario3ActionPerformed

    private void Prontuario4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prontuario4ActionPerformed
         final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "Pacientes.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
    }//GEN-LAST:event_Prontuario4ActionPerformed

    private void Prontuario5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prontuario5ActionPerformed
         final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/hospital";
        try {
            Class.forName(driver);
            com.mysql.jdbc.Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "ifrs");
            String src = "RelatorioFuncionarios.jasper";
            JasperPrint jp = null;

            try {
                jp = JasperFillManager.fillReport(src, null, con);
            } catch (JRException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(" ");
        } catch (ClassNotFoundException ex) {
            System.out.println(" ");
        }
    }//GEN-LAST:event_Prontuario5ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaDeCadastros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeCadastros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeCadastros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeCadastros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDeCadastros().setVisible(true);

            }
        });
        
        /*

        System.out.println("Servidor Iniciado");
        ServerSocket listener = new ServerSocket(3090);

        try {
            while (true) {
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                System.out.println("Client Connected");
                Conectado = "sim";
                Pac.enviaTextoConectado(Conectado);
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
        */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Funcionario;
    private javax.swing.JButton Medicamento;
    private javax.swing.JButton Medico;
    private javax.swing.JButton Paciente;
    private javax.swing.JButton Prontuario;
    private javax.swing.JButton Prontuario1;
    private javax.swing.JButton Prontuario2;
    private javax.swing.JButton Prontuario3;
    private javax.swing.JButton Prontuario4;
    private javax.swing.JButton Prontuario5;
    private javax.swing.JButton Quarto;
    private javax.swing.JButton Quartos;
    private javax.swing.JDesktopPane TelaCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the Login
     */
    public String getLogin() {
        System.out.println(Login);
        return Login;

    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

}
