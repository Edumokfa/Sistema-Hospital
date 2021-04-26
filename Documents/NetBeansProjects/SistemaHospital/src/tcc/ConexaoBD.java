
package tcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *
 * @autor Eduardo Mokfa
 */
public class ConexaoBD {


    public static void main(String[] args) {

        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/Mysql";
        
        try {
            Class.forName(driver);
            Connection con = (Connection) DriverManager.getConnection(url, "root", "ifrs");
            JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso");
            con.close();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro na realização da conexão");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados");
        }

        geraBd();
        geraTab();
        //consultBd();

    }

    public static void geraBd() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "ifrs");

            Statement st = con.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS hospital";
            st.executeUpdate(sql);
            System.out.println(sql);

            JOptionPane.showMessageDialog(null, "Banco de dados criado com sucesso");
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado");
        }
    }

    public static void geraTab() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "ifrs");
            Statement st = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `hospital`.`Medicos` ("
                    + "`IdMedico` INT NOT NULL AUTO_INCREMENT,"
                    + "`RfidMedico` VARCHAR(20) NOT NULL,"
                    + "`CpfMedico` VARCHAR(20) NOT NULL,"
                    + "`RgMedico` VARCHAR(15) NOT NULL,"
                    + "`CrmMedico` VARCHAR(45) NOT NULL,"
                    + "`NomeMedico` VARCHAR(45) NOT NULL,"
                    + "`EnderecoMedico` VARCHAR(45) NOT NULL,"
                    + "`BairroMedico` VARCHAR(45) NOT NULL,"
                    + "`CidadeMedico` VARCHAR(45) NOT NULL,"
                    + "`SexoMedico` VARCHAR(45) NOT NULL,"
                    + "`FormacaoMedico` VARCHAR(45) NOT NULL,"
                    + "`AreaAtuacaoMedico` VARCHAR(45) NOT NULL,"
                    + "`LoginMedico` VARCHAR(45) NOT NULL,"
                    + "`SenhaMedico` VARCHAR(85) NOT NULL,"
                    + "PRIMARY KEY (`IdMedico`),"
                    + "UNIQUE INDEX `SenhaMedico_UNIQUE` (`SenhaMedico` ASC) ,"
                    + "UNIQUE INDEX `EnderecoMedico_UNIQUE` (`EnderecoMedico` ASC) ,"
                    + "UNIQUE INDEX `IdMedico_UNIQUE` (`IdMedico` ASC) ,"
                    + "UNIQUE INDEX `RfidMedico_UNIQUE` (`RfidMedico` ASC) ,"
                    + "UNIQUE INDEX `CpfMedico_UNIQUE` (`CpfMedico` ASC) ,"
                    + "UNIQUE INDEX `RgMedico_UNIQUE` (`RgMedico` ASC) ,"
                    + "UNIQUE INDEX `CrmMedico_UNIQUE` (`CrmMedico` ASC))";
            st.executeUpdate(sql);
            System.out.println(sql);

            String sql2 = "CREATE TABLE IF NOT EXISTS `hospital`.`Funcionarios` ("
                    + "  `IdFuncionario` INT NOT NULL AUTO_INCREMENT,"
                    + "  `RfidFuncionario` VARCHAR(20) NOT NULL,"
                    + "  `CpfFuncionario` VARCHAR(20) NOT NULL,"
                    + "  `RgFuncionario` VARCHAR(20) NOT NULL,"
                    + "  `NomeFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `EnderecoFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `BairroFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `CidadeFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `SexoFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `AreaAtuacaoFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `FormacaoFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `LoginFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `SenhaFuncionario` VARCHAR(85) NOT NULL,"
                    + "  PRIMARY KEY (`IdFuncionario`),"
                    + "  UNIQUE INDEX `LoginFuncionario_UNIQUE` (`LoginFuncionario` ASC) ,"
                    + "  UNIQUE INDEX `SenhaFuncionario_UNIQUE` (`SenhaFuncionario` ASC) ,"
                    + "  UNIQUE INDEX `IdFuncionario_UNIQUE` (`IdFuncionario` ASC) ,"
                    + "  UNIQUE INDEX `RfidFuncionario_UNIQUE` (`RfidFuncionario` ASC) ,"
                    + "  UNIQUE INDEX `CpfFuncionario_UNIQUE` (`CpfFuncionario` ASC) ,"
                    + "  UNIQUE INDEX `RgFuncionario_UNIQUE` (`RgFuncionario` ASC) )";

            st.executeUpdate(sql2);
            System.out.println(sql2);

            String sql3 = "CREATE TABLE IF NOT EXISTS `hospital`.`Quarto` ("
                    + "  `Numero` INT NOT NULL AUTO_INCREMENT,"
                    + "  `PavimentoQuarto` VARCHAR(45) NOT NULL,"
                    + "  `FuncaoQuarto` VARCHAR(45) NOT NULL,"
                    + "  PRIMARY KEY (`Numero`),"
                    + "  UNIQUE INDEX `Numero_UNIQUE` (`Numero` ASC) )";

            st.executeUpdate(sql3);
            System.out.println(sql3);

            String sql4 = "CREATE TABLE IF NOT EXISTS `hospital`.`Pacientes_has_Quarto` ("
                    + "  `Quarto_Numero` INT NOT NULL AUTO_INCREMENT,"
                    + "  `Data_entrada` VARCHAR(45) NOT NULL,"
                    + "  `Data_saida` VARCHAR(45) NOT NULL,"
                    + "  `Quarto_Numero1` INT NOT NULL,"
                    + "  PRIMARY KEY (`Quarto_Numero`),"
                    + "  INDEX `fk_Pacientes_has_Quarto_Quarto1_idx` (`Quarto_Numero1` ASC) ,"
                    + "  CONSTRAINT `fk_Pacientes_has_Quarto_Quarto1`"
                    + "    FOREIGN KEY (`Quarto_Numero1`)"
                    + "    REFERENCES `hospital`.`Quarto` (`Numero`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION)";

            st.executeUpdate(sql4);
            System.out.println(sql4);

            String sql5 = "CREATE TABLE IF NOT EXISTS `hospital`.`Pacientes` ("
                    + "  `IdPaciente` INT NOT NULL AUTO_INCREMENT,"
                    + "  `CpfPaciente` VARCHAR(20) NOT NULL,"
                    + "  `RgPaciente` VARCHAR(20) NOT NULL,"
                    + "  `NomePaciente` VARCHAR(45) NOT NULL,"
                    + "  `EnderecoPaciente` VARCHAR(45) NOT NULL,"
                    + "  `BairroPaciente` VARCHAR(45) NOT NULL,"
                    + "  `CidadePaciente` VARCHAR(45) NOT NULL,"
                    + "  `SexoPaciente` VARCHAR(9) NOT NULL,"
                    + "  `Pacientes_has_Quarto_Quarto_Numero` INT NULL,"
                    + "  PRIMARY KEY (`IdPaciente`),"
                    + "  INDEX `fk_Pacientes_Pacientes_has_Quarto1_idx` (`Pacientes_has_Quarto_Quarto_Numero` ASC) ,"
                    + "  UNIQUE INDEX `CpfPaciente_UNIQUE` (`CpfPaciente` ASC) ,"
                    + "  UNIQUE INDEX `RgPaciente_UNIQUE` (`RgPaciente` ASC) ,"
                    + "  UNIQUE INDEX `IdPaciente_UNIQUE` (`IdPaciente` ASC) ,"
                    + "  CONSTRAINT `fk_Pacientes_Pacientes_has_Quarto1`"
                    + "    FOREIGN KEY (`Pacientes_has_Quarto_Quarto_Numero`)"
                    + "    REFERENCES `hospital`.`Pacientes_has_Quarto` (`Quarto_Numero`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION)";
            st.executeUpdate(sql5);
            System.out.println(sql5);

            String sql6 = "CREATE TABLE IF NOT EXISTS `hospital`.`Prontuario` ("
                    + "  `idProntuario` INT NOT NULL AUTO_INCREMENT,"
                    + "  `DataInternacao` VARCHAR(45) NOT NULL,"
                    + "  `MotivoInternacao` VARCHAR(45) NOT NULL,"
                    + "  `HoraInternacao` VARCHAR(45) NOT NULL,"
                    + "  `DiagnosticoInternacao` VARCHAR(45) NOT NULL,"
                    + "  `Medicamentos` VARCHAR(75) NOT NULL,"
                    + "`Alta_Paciente` VARCHAR(4) NOT NULL,"
                    + "  `Pacientes_IdPaciente` INT NOT NULL,"
                    + "  `Medicos_IdMedico` INT NOT NULL,"
                    + "  `Quarto_Numero` INT NOT NULL,"
                    + "  PRIMARY KEY (`idProntuario`),"
                    + "  INDEX `fk_Prontuario_Pacientes1_idx` (`Pacientes_IdPaciente` ASC) ,"
                    + "  INDEX `fk_Prontuario_Medicos1_idx` (`Medicos_IdMedico` ASC) ,"
                    + "  INDEX `fk_Prontuario_Quarto1_idx` (`Quarto_Numero` ASC) ,"
                    + "  UNIQUE INDEX `idProntuario_UNIQUE` (`idProntuario` ASC) ,"
                    + "  CONSTRAINT `fk_Prontuario_Pacientes1`"
                    + "    FOREIGN KEY (`Pacientes_IdPaciente`)"
                    + "    REFERENCES `hospital`.`Pacientes` (`IdPaciente`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Prontuario_Medicos1`"
                    + "    FOREIGN KEY (`Medicos_IdMedico`)"
                    + "    REFERENCES `hospital`.`Medicos` (`IdMedico`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Prontuario_Quarto1`"
                    + "    FOREIGN KEY (`Quarto_Numero`)"
                    + "    REFERENCES `hospital`.`Quarto` (`Numero`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION)";

            st.executeUpdate(sql6);
            System.out.println(sql6);

            String sql7 = "CREATE TABLE IF NOT EXISTS `hospital`.`Medicamentos` ("
                    + "  `idMedicamento` INT NOT NULL AUTO_INCREMENT,"
                    + "  `NomeComercial` VARCHAR(45) NOT NULL,"
                    + "  `NomeQuimico` VARCHAR(45) NOT NULL,"
                    + "  `ResumoMedicamento` VARCHAR(45) NOT NULL,"
                    + "  `UnidadeMedicamento` VARCHAR(15) NOT NULL,"
                    + "  `ClassificacaoFarmacologica` VARCHAR(45) NOT NULL,"
                    + "  `IntervaloDeTempo` VARCHAR(20) NOT NULL,"
                    + "  PRIMARY KEY (`idMedicamento`),"
                    + "  UNIQUE INDEX `idMedicamento_UNIQUE` (`idMedicamento` ASC) )";

            st.executeUpdate(sql7);
            System.out.println(sql7);
            String sql8 = "CREATE TABLE IF NOT EXISTS `hospital`.`Prescricao` ("
                    + "  `idPrescricao` INT NOT NULL,"
                    + "  `Horarios` VARCHAR(45) NOT NULL,"
                    + "  `Quantidade` INT(6) NOT NULL,"
                    + "  `InfoAdicional` VARCHAR(45) NULL,"
                    + "  `Medicos_IdMedico` INT NOT NULL,"
                    + "  `Pacientes_IdPaciente` INT NOT NULL,"
                    + "  `Medicamentos_idMedicamento` INT NOT NULL,"
                    + "  PRIMARY KEY (`idPrescricao`),"
                    + "  INDEX `fk_Prescricao_Medicos1_idx` (`Medicos_IdMedico` ASC) ,"
                    + "  INDEX `fk_Prescricao_Pacientes1_idx` (`Pacientes_IdPaciente` ASC) ,"
                    + "  INDEX `fk_Prescricao_Medicamentos1_idx` (`Medicamentos_idMedicamento` ASC) ,"
                    + "  CONSTRAINT `fk_Prescricao_Medicos1`"
                    + "    FOREIGN KEY (`Medicos_IdMedico`)"
                    + "    REFERENCES `hospital`.`Medicos` (`IdMedico`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Prescricao_Pacientes1`"
                    + "    FOREIGN KEY (`Pacientes_IdPaciente`)"
                    + "    REFERENCES `hospital`.`Pacientes` (`IdPaciente`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Prescricao_Medicamentos1`"
                    + "    FOREIGN KEY (`Medicamentos_idMedicamento`)"
                    + "    REFERENCES `hospital`.`Medicamentos` (`idMedicamento`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION)";
            st.executeUpdate(sql8);
            System.out.println(sql8);
            String sql9 = "CREATE TABLE IF NOT EXISTS `hospital`.`Procedimentos` ("
                    + "  `Medicos_IdMedico` INT NOT NULL,"
                    + "  `Descricao` VARCHAR(45) NOT NULL,"
                    + "  `Prontuario_idProntuario` INT NOT NULL,"
                    + "  `Funcionarios_IdFuncionario` INT NOT NULL,"
                    + "  `Medicos_IdMedico1` INT NOT NULL,"
                    + "  INDEX `fk_Procedimentos_Prontuario1_idx` (`Prontuario_idProntuario` ASC) ,"
                    + "  INDEX `fk_Procedimentos_Funcionarios1_idx` (`Funcionarios_IdFuncionario` ASC) ,"
                    + "  INDEX `fk_Procedimentos_Medicos1_idx` (`Medicos_IdMedico1` ASC) ,"
                    + "  CONSTRAINT `fk_Procedimentos_Prontuario1`"
                    + "    FOREIGN KEY (`Prontuario_idProntuario`)"
                    + "    REFERENCES `hospital`.`Prontuario` (`idProntuario`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Procedimentos_Funcionarios1`"
                    + "    FOREIGN KEY (`Funcionarios_IdFuncionario`)"
                    + "    REFERENCES `hospital`.`Funcionarios` (`IdFuncionario`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Procedimentos_Medicos1`"
                    + "    FOREIGN KEY (`Medicos_IdMedico1`)"
                    + "    REFERENCES `hospital`.`Medicos` (`IdMedico`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION)";
            st.executeUpdate(sql9);
            System.out.println(sql9);

            String sql10 = "CREATE TABLE IF NOT EXISTS `hospital`.`Atendimento_Medico` ("
                    + "  `idAtendimento_Medico` INT NOT NULL AUTO_INCREMENT,"
                    + "  `Pacientes_IdPaciente` INT NOT NULL,"
                    + "  `Quarto_Numero` INT NOT NULL,"
                    + "  `Prontuario_idProntuario` INT NOT NULL,"
                    + "  `Funcionarios_IdFuncionario` INT NOT NULL,"
                    + "  `NomeFuncionario` VARCHAR(45) NOT NULL,"
                    + "  `Data_Atendimento` VARCHAR(45) NOT NULL,"
                    + "  `Hora_Solicitado` VARCHAR(45) NOT NULL,"
                    + "  `Hora_Atendimento` VARCHAR(45) NOT NULL,"
                    + "  PRIMARY KEY (`idAtendimento_Medico`),"
                    + "  INDEX `fk_Atendimento_Medico_Pacientes1_idx` (`Pacientes_IdPaciente` ASC) ,"
                    + "  INDEX `fk_Atendimento_Medico_Quarto1_idx` (`Quarto_Numero` ASC) ,"
                    + "  INDEX `fk_Atendimento_Medico_Prontuario1_idx` (`Prontuario_idProntuario` ASC) ,"
                    + "  INDEX `fk_Atendimento_Medico_Funcionarios1_idx` (`Funcionarios_IdFuncionario` ASC) ,"
                    + "  UNIQUE INDEX `idAtendimento_Medico_UNIQUE` (`idAtendimento_Medico` ASC) ,"
                    + "  CONSTRAINT `fk_Atendimento_Medico_Pacientes1`"
                    + "    FOREIGN KEY (`Pacientes_IdPaciente`)"
                    + "    REFERENCES `hospital`.`Pacientes` (`IdPaciente`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Atendimento_Medico_Quarto1`"
                    + "    FOREIGN KEY (`Quarto_Numero`)"
                    + "    REFERENCES `hospital`.`Quarto` (`Numero`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Atendimento_Medico_Prontuario1`"
                    + "    FOREIGN KEY (`Prontuario_idProntuario`)"
                    + "    REFERENCES `hospital`.`Prontuario` (`idProntuario`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION,"
                    + "  CONSTRAINT `fk_Atendimento_Medico_Funcionarios1`"
                    + "    FOREIGN KEY (`Funcionarios_IdFuncionario`)"
                    + "    REFERENCES `hospital`.`Funcionarios` (`IdFuncionario`)"
                    + "    ON DELETE NO ACTION"
                    + "    ON UPDATE NO ACTION)";
            st.executeUpdate(sql10);
            System.out.println(sql10);
            
            String sql11 = "INSERT INTO funcionarios(NomeFuncionario, EnderecoFuncionario, BairroFuncionario, CidadeFuncionario, RfidFuncionario, RgFuncionario, CpfFuncionario, AreaAtuacaoFuncionario, FormacaoFuncionario,LoginFuncionario,SenhaFuncionario, SexoFuncionario) VALUES ('Admin','XXX','XXX','XXX','11','1111111111','111.111.111-11','XXX','XXX','admin','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918','Masculino')";
            st.executeUpdate(sql11);
            System.out.println(sql11);
            
            JOptionPane.showMessageDialog(null, "Tabelas criadas");
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas na conexão com a fonte de dados");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado");
        }
    }

}
