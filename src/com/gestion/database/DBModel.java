package com.gestion.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBModel {

	Properties properties = new Properties();
    InputStream inputStream;
    String db;
    
    
    
    public void loadPropertiesFile(){
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            db = properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("DDDD");
        }
    }

    PreparedStatement pst;

    public void createDataBase() {
        loadPropertiesFile();
        DBConnection con = new DBConnection();
        try {
            pst = con.mkDataBase().prepareStatement("create database if not exists "+db+" DEFAULT CHARACTER SET utf8 \n"
                    + "  DEFAULT COLLATE utf8_general_ci");
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Camion` (\n"
                    + "  `idCamion` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Marca` VARCHAR(45) NULL,\n"
                    + "  `Modelo` VARCHAR(45) NULL,\n"
                    + "  `Camioncol` VARCHAR(45) NULL,\n"
                    + "  `PATENTE` VARCHAR(6) NULL,\n"
                    + "  PRIMARY KEY (`idCamion`))\n"
                    + "ENGINE = InnoDB;");
            
            
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`CategoriaEmpleado` (\n"
                    + "  `idCategoriaEmpleado` INT NOT NULL AUTO_INCREMENT,"
                    + "  `NombreCategoria` VARCHAR(45) NULL,"
                    + "  PRIMARY KEY (`idCategoriaEmpleado`))"
                    + "ENGINE = InnoDB;");
            /*
             * 
             * */
            pst.execute();
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Persona` (\n"
                    + "  `idPersona` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nombre` VARCHAR(45) NULL,\n"
                    + "  `Apellido` VARCHAR(45) NULL,\n"
                    + "  `DNI` VARCHAR(45) NULL,\n"
                    + "  `FechaNacimiento` VARCHAR(45) NULL,\n"
                    + "  `Observaciones` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idPersona`))"
                    + "ENGINE = InnoDB;");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`TURNO` (\n"
                    + "  `idTURNO` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `hora_inicio_turno` VARCHAR(45) NULL,\n"
                    + "  `hora_fin_turno` VARCHAR(45) NULL,\n"
                    + "  `descripcion` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idTURNO`))"
                    + "ENGINE = InnoDB;");
            pst.execute();

           
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Empleado` (\n"
                    + "  `idEmpleado` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `CUIT` VARCHAR(11) NULL,\n"
                    + "  `CategoriaEmpleado_idCategoriaEmpleado` INT NOT NULL,\n"
                    + "  `Persona_idPersona` INT NOT NULL,\n"
                    + "  `TURNO_idTURNO` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idEmpleado`, `CategoriaEmpleado_idCategoriaEmpleado`,"
                    + " `Persona_idPersona`, `TURNO_idTURNO`),\n"
                    + "  CONSTRAINT `fk_Empleado_CategoriaEmpleado1`\n"
                    + "  FOREIGN KEY (`CategoriaEmpleado_idCategoriaEmpleado`)\n"
                    + "  REFERENCES `mydb`.`CategoriaEmpleado`(`idCategoriaEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Empleado_Persona1`\n"
                    + "  FOREIGN KEY (`Persona_idPersona`)\n"
                    + "   REFERENCES `mydb`.`Persona` (`idPersona`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Empleado_TURNO1`\n"
                    + "  FOREIGN KEY (`TURNO_idTURNO`)\n"
                    + "  REFERENCES `mydb`.`TURNO` (`idTURNO`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB\n"
                    + "  PACK_KEYS = DEFAULT;");

            pst.execute();
/*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Empleado_CategoriaEmpleado1_idx` ON `mydb`.`Empleado` (`CategoriaEmpleado_idCategoriaEmpleado` ASC);");

            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Empleado_Persona1_idx`"
            		+ " ON `mydb`.`Empleado`(`Persona_idPersona` ASC);");

            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Empleado_TURNO1_idx`"
            		+ " ON `mydb`.`Empleado`(`TURNO_idTURNO` ASC);");

            pst.execute();
            
  */          
            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Localidad` (\n"
                    + "  `idLocalidad` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `NombreLocalidad` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idLocalidad`))\n"
                    + "  ENGINE = InnoDB;");

            pst.execute();


            pst = con.mkDataBase().prepareStatement("CREATE TABLE if not exists "+db+".`Provincia` (\n"
                    + "  `idProvincia` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `NombreProvincia` VARCHAR(45) NULL,\n"
                    + "   PRIMARY KEY (`idProvincia`))\n"
                    + "  ENGINE = InnoDB;");

            pst.execute();
       
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Direccion` (\n"
                    + "  `idDireccion` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Calle` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idDireccion`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();

           
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Domicilio` (\n"
                    + "  `idDomicilio` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Localidad_idLocalidad` INT NOT NULL,\n"
                    + "  `Provincia_idProvincia` INT NOT NULL,\n"
                    + "  `Direccion_idDireccion` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idDomicilio`, `Localidad_idLocalidad`,"
                    + "  `Provincia_idProvincia`, `Direccion_idDireccion`),\n"
                    + "  CONSTRAINT `fk_Domicilio_Localidad1`\n"
                    + "  FOREIGN KEY (`Localidad_idLocalidad`)\n"
                    + "  REFERENCES `mydb`.`Localidad` (`idLocalidad`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Domicilio_Provincia1`\n"
                    + "  FOREIGN KEY (`Provincia_idProvincia`)\n"
                    + "  REFERENCES `mydb`.`Provincia` (`idProvincia`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Domicilio_Direccion1`\n"
                    + "  FOREIGN KEY (`Direccion_idDireccion`)\n"
                    + "  REFERENCES `mydb`.`Direccion` (`idDireccion`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            
         
  /*          pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Domicilio_Localidad1_idx` "
            		+ "ON" + db +".`Domicilio` (`Localidad_idLocalidad` ASC);");

            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Domicilio_Provincia1_idx` "
            		+ "ON" + db +".`Domicilio` (`Provincia_idProvincia` ASC);");

            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Domicilio_Direccion1_idx` "
            		+ "ON" + db +".`Domicilio` (`Direccion_idDireccion` ASC);");

            pst.execute();
            
       */     
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Proveedor` (\n"
                    + "  `idProveedor` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nombre` VARCHAR(45) NULL,\n"
                    + "  `Direccion` VARCHAR(45) NULL,\n"
                    + "  `CUIT` VARCHAR(11) NULL,\n"
                    + "  `Domicilio_idDomicilio` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idProveedor`, `Domicilio_idDomicilio`),\n"
                    + "  CONSTRAINT `fk_Proveedor_Domicilio1`\n"
                    + "  FOREIGN KEY (`Domicilio_idDomicilio`)\n"
                    + "  REFERENCES `mydb`.`Domicilio` (`idDomicilio`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
           /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Proveedor_Domicilio1_idx` "
            		+ "ON" + db +".`Proveedor` (`Direccion_idDireccion` ASC);");

            pst.execute();
           */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Finca` (\n"
                    + "  `idFinca` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Dueño_idDueño` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idFinca`, `Dueño_idDueño`),\n"
                    + "  CONSTRAINT `fk_Finca_Dueño1` \n"
                    + "  FOREIGN KEY (`Dueño_idDueño`)\n"
                    + "  REFERENCES `mydb`.`Dueño` (`idDueño`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
         
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Dueño` (\n"
                    + "  `idDueño` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Nombre` VARCHAR(45) NULL,\n"
                    + "  `CUIT` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idDueño`)) \n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
           
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Ingenio` (\n"
                    + "  `idIngenio` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Dueño_idDueño` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idIngenio`, `Dueño_idDueño`),\n"
                    + "  CONSTRAINT `fk_Ingenio_Dueño1` \n"
                    + "  FOREIGN KEY (`Dueño_idDueño`)\n"
                    + "  REFERENCES `mydb`.`Dueño` (`idDueño`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Ingenio_Dueño1_idx` "
            		+ "ON" + db +".`Ingenio` (`Dueño_idDueño` ASC);");

            pst.execute();
           */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Finca` (\n"
                    + "  `idFinca` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Dueño_idDueño` INT NOT NULL,\n"
                    + " PRIMARY KEY (`idFinca`, `Dueño_idDueño`),\n"
                    + "  CONSTRAINT `fk_Finca_Dueño1`\n"
                    + "  FOREIGN KEY (`Dueño_idDueño`)\n"
                    + "  REFERENCES `mydb`.`Dueño` (`idDueño`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Finca_Dueño1_idx` "
            		+ "ON" + db +".`Finca` (`Dueño_idDueño` ASC);");

            pst.execute();
            */
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Origen_Destino` (\n"
                    + "  `idOrigen_Destino` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Ingenio_idIngenio` INT NOT NULL,\n"
                    + "  `Finca_idFinca` INT NOT NULL,\n"
                    + "  `DistanciaKM` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idOrigen_Destino`, `Ingenio_idIngenio`, `Finca_idFinca`),\n"
                    + "  CONSTRAINT `fk_Origen_Destino_Ingenio1`\n"
                    + "  FOREIGN KEY (`Ingenio_idIngenio`)\n"
                    + "  REFERENCES `mydb`.`Finca` (`idFinca`)\n"
                    + "  ON DELETE NO ACTION"
                    + "  ON UPDATE NO ACTION)"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
           /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Origen_Destino_Ingenio1_idx` "
            		+ "ON" + db +".`Origen_Destino` (`Ingenio_idIngenio` ASC);");

            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Origen_Destino_Finca1_idx` "
            		+ "ON" + db +".`Origen_Destino` (`Finca_idFinca` ASC);");

            pst.execute();
            
          */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Remito` (\n"
                    + "  `idRemito` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Fecha` DATE NULL,\n"
                    + "  `HoraEntrada` TIME NULL,\n"
                    + "  `HoraSalida` TIME NULL,\n"
                    + "  `Bruto` VARCHAR(45) NULL,\n"
                    + "  `Tara` VARCHAR(45) NULL,\n"
                    + "  `Trash` VARCHAR(45) NULL,\n"
                    + "  `Origen_Destino_idOrigen_Destino` INT NOT NULL,\n"
                    + "  `Empleado_idEmpleado` INT NOT NULL,\n"
                    + "	 `Camion_idCamion` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idRemito`, `Origen_Destino_idOrigen_Destino`, `Empleado_idEmpleado`,"
                    + " `Camion_idCamion`),\n"
                    + "  CONSTRAINT `fk_Remito_Origen_Destino1`\n"
                    + "  FOREIGN KEY (`Origen_Destino_idOrigen_Destino`)\n"
                    + "  REFERENCES `mydb`.`Empleado` (`idEmpleado`)\n"
                    + "	 ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Remito_Empleado1`\n"
                    + "  FOREIGN KEY (`Empleado_idEmpleado`)\n"
                    + "  REFERENCES `mydb`.`Empleado` (`idEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Remito_Camion1`\n"
                    + "  FOREIGN KEY (`Camion_idCamion`)\n"
                    + " REFERENCES `mydb`.`Camion` (`idCamion`)\n"
                    + "   ON DELETE NO ACTION\n"
                    + "   ON UPDATE NO ACTION)"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
           /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Remito_Origen_Destino1_idx` "
            		+ "ON" + db +".`Remito` (`Origen_Destino_idOrigen_Destino` ASC);");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Remito_Empleado1_idx` "
            		+ "ON" + db +".`Remito` (`Empleado_idEmpleado` ASC);");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Remito_Camion1_idx` "
            		+ "ON" + db +".`Remito` (`Camion_idCamion` ASC);");
            pst.execute();
            
           */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`TipoDeServicio` (\n"
                    + "  `idTipoDeServicio` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `NombreServicio` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idTipoDeServicio`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
           
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`FacturaCompraArticulo` (\n"
                    + "  `idFacturaCompraArticulo` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Fecha` DATE NULL,\n"
                    + "  PRIMARY KEY (`idFacturaCompraArticulo`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`CategoriaArticulo` (\n"
                    + "  `idCategoriaArticulo` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `NombreCategoria` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idCategoriaArticulo`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Articulo` (\n"
                    + "  `idArticulo` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Marca` VARCHAR(45) NULL,\n"
                    + "  `Modelo` VARCHAR(45) NULL,\n"
                    + "  `Descripcion` VARCHAR(45) NULL,\n"
                    + "  `CategoriaArticulo_idCategoriaArticulo` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idArticulo`, `CategoriaArticulo_idCategoriaArticulo`),\n"
                    + "  CONSTRAINT `fk_Articulo_CategoriaArticulo1`\n"
                    + "  FOREIGN KEY (`CategoriaArticulo_idCategoriaArticulo`)\n"
                    + "  REFERENCES `mydb`.`CategoriaArticulo` (`idCategoriaArticulo`)\n"
                    + "	 ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Articulo_CategoriaArticulo1_idx` "
            		+ "ON" + db +".`Articulo` (`CategoriaArticulo_idCategoriaArticulo` ASC);");
            pst.execute();
            
            */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`CompraArticulo` (\n"
                    + "  `idCompraArticulo` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Cantidad` TINYINT(4) NULL,\n"
                    + "  `PrecioUnitario` FLOAT NULL,\n"
                    + "  `Articulo_idArticulo` INT NOT NULL,\n"
                    + "  `FacturaCompraArticulo_idFacturaCompraArticulo` INT NOT NULL,\n"
                    + "  `Proveedor_idProveedor` INT NOT NULL,\n"
                    + "   PRIMARY KEY (`idCompraArticulo`, `Articulo_idArticulo`, "
                    + "`FacturaCompraArticulo_idFacturaCompraArticulo`, `Proveedor_idProveedor`),\n"
                    + "  CONSTRAINT `fk_CompraArticulo_Articulo1`\n"
                    + "  FOREIGN KEY (`Articulo_idArticulo`)\n"
                    + "	 REFERENCES `mydb`.`Articulo` (`idArticulo`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_CompraArticulo_FacturaCompraArticulo1`\n"
                    + "  FOREIGN KEY (`FacturaCompraArticulo_idFacturaCompraArticulo`)\n"
                    + "  REFERENCES `mydb`.`FacturaCompraArticulo` (`idFacturaCompraArticulo`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_CompraArticulo_Proveedor1`\n"
                    + "  FOREIGN KEY (`Proveedor_idProveedor`)\n"
                    + "  REFERENCES `mydb`.`Proveedor` (`idProveedor`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_CompraArticulo_Articulo1_idx`  "
            		+ "ON" + db +".`CompraArticulo` (`Articulo_idArticulo` ASC);");
            pst.execute();  
            
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_CompraArticulo_FacturaCompraArticulo1_idx` "
            		+ "ON" + db +".`CompraArticulo` (`FacturaCompraArticulo_idFacturaCompraArticulo` ASC);");
            pst.execute(); 
            
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_CompraArticulo_Proveedor1_idx` "
            		+ "ON" + db +".`CompraArticulo` (`Proveedor_idProveedor` ASC);");
            pst.execute();  
            */
           
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Usuario` (\n"
                    + "  `idUsuario` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `NombreUsuario` VARCHAR(45) NULL,\n"
                    + "  `Password` VARCHAR(45) NULL,\n"
                    + "  `Empleado_idEmpleado` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idUsuario`, `Empleado_idEmpleado`),\n"
                    + "  CONSTRAINT `fk_Usuario_Empleado1`\n"
                    + "   FOREIGN KEY (`Empleado_idEmpleado`)\n"
                    + "  REFERENCES `mydb`.`Empleado` (`idEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Usuario_Empleado1_idx` "
            		+ "ON" + db +".`Usuario` (`Empleado_idEmpleado` ASC);");
            pst.execute();  
              */        
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`TIPO_LIQ` (\n"
                    + "  `idTIPO_LIQ` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `nombre` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idTIPO_LIQ`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`PERIODO_LIQ` (\n"
                    + "  `idPERIODO_LIQ` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `periodo` VARCHAR(20) NULL,\n"
                    + "  PRIMARY KEY (`idPERIODO_LIQ`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`LiquidacionEmpleado` (\n"
                    + "  `idLiquidacionEmpleado` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `fecha_liquidacion` DATE NULL,\n"
                    + "  `importe_neto` DOUBLE NULL,\n"
                    + "  `total_haberes_remunerativos` DOUBLE NULL,\n"
                    + "  `total_haberes_no_remunerativos` DOUBLE NULL,\n"
                    + "  `total_retenciones` DOUBLE NULL,\n"
                    + "  `total_bruto` DOUBLE NULL, "
                    + "  `TIPO_LIQ_idTIPO_LIQ` INT NOT NULL,\n"
                    + "  `PERIODO_LIQ_idPERIODO_LIQ` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idLiquidacionEmpleado`, `TIPO_LIQ_idTIPO_LIQ`, `PERIODO_LIQ_idPERIODO_LIQ`),\n"
                    + "	 CONSTRAINT `fk_LiquidacionEmpleado_TIPO_LIQ1`\n"
                    + "  FOREIGN KEY (`TIPO_LIQ_idTIPO_LIQ`)\n"
                    + "  REFERENCES `mydb`.`TIPO_LIQ` (`idTIPO_LIQ`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_LiquidacionEmpleado_TIPO_LIQ1_idx` "
            		+ "ON" + db +".`LiquidacionEmpleado` (`TIPO_LIQ_idTIPO_LIQ` ASC);");
            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_LiquidacionEmpleado_PERIODO_LIQ1_idx` "
            		+ "ON" + db +".`LiquidacionEmpleado` (`PERIODO_LIQ_idPERIODO_LIQ` ASC);");
            pst.execute();
                        */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`TIPO_CONCEPTO` (\n"
                    + "  `idTIPO_CONCEPTO` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `descripcion` VARCHAR(30) NULL,\n"
                    + "  PRIMARY KEY (`idTIPO_CONCEPTO`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`CONCEPTO_SUELDO` (\n"
                    + "  `idCONCEPTO_SUELDO` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `descripcion` VARCHAR(45) NULL,\n"
                    + "   `importe` VARCHAR(45) NULL,\n"
                    + "  `unidad` VARCHAR(45) NULL,\n"
                    + "  `porcentaje` VARCHAR(45) NULL,\n"
                    + "  `estado` VARCHAR(45) NULL,\n"
                    + "  `observaciones` VARCHAR(45) NULL,\n"
                    + "  `TIPO_CONCEPTO_idTIPO_CONCEPTO` INT NOT NULL,\n"
                    + " PRIMARY KEY (`idCONCEPTO_SUELDO`, `TIPO_CONCEPTO_idTIPO_CONCEPTO`),\n"
                    + "  CONSTRAINT `fk_CONCEPTO_SUELDO_TIPO_CONCEPTO1`\n"
                    + "	 FOREIGN KEY (`TIPO_CONCEPTO_idTIPO_CONCEPTO`)\n"
                    + "  REFERENCES `mydb`.`TIPO_CONCEPTO` (`idTIPO_CONCEPTO`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
           /* 
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_CONCEPTO_SUELDO_TIPO_CONCEPTO1_idx` "
            		+ "ON" + db +".`CONCEPTO_SUELDO` (`TIPO_CONCEPTO_idTIPO_CONCEPTO` ASC);");
            pst.execute();
             */         
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`DetalleLiquidacionEmpleado` (\n"
                    + "  `idDetalleLiquidacionEmpleado` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `haberes_remunerativos` DOUBLE NULL,\n"
                    + "  `haberes_no_remunerativos` DOUBLE NULL,\n"
                    + "  `retenciones` DOUBLE NULL,\n"
                    + "  `LiquidacionEmpleado_idLiquidacionEmpleado` INT NOT NULL,\n"
                    + "  `CONCEPTO_SUELDO_idCONCEPTO_SUELDO` INT NOT NULL,\n"
                    + "   PRIMARY KEY (`idDetalleLiquidacionEmpleado`, `LiquidacionEmpleado_idLiquidacionEmpleado`,"
                    + " `CONCEPTO_SUELDO_idCONCEPTO_SUELDO`), \n"
                    
                    + "   CONSTRAINT `fk_DetalleLiquidacionEmpleado_LiquidacionEmpleado1`\n"
                    + "  FOREIGN KEY (`LiquidacionEmpleado_idLiquidacionEmpleado`)\n"
                    + "	 REFERENCES `mydb`.`LiquidacionEmpleado` (`idLiquidacionEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "   CONSTRAINT `fk_DetalleLiquidacionEmpleado_CONCEPTO_SUELDO1`\n"
                    + "  FOREIGN KEY (`CONCEPTO_SUELDO_idCONCEPTO_SUELDO`)\n"
                    + "  REFERENCES `mydb`.`CONCEPTO_SUELDO` (`idCONCEPTO_SUELDO`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
          /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_DetalleLiquidacionEmpleado_LiquidacionEmpleado1_idx` "
            		+ "ON" + db +".`DetalleLiquidacionEmpleado` (`LiquidacionEmpleado_idLiquidacionEmpleado` ASC);");
            pst.execute();
                      
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_DetalleLiquidacionEmpleado_CONCEPTO_SUELDO1_idx` "
            		+ "ON" + db +".`DetalleLiquidacionEmpleado` (`CONCEPTO_SUELDO_idCONCEPTO_SUELDO` ASC);");
            pst.execute();
            */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`RECIBO_SUELDO` (\n"
                    + "  `idRECIBO_SUELDO` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `fecha` DATE NULL,\n"
                    + "  `total_neto` DOUBLE NULL,\n"
                    + "  `nro_recibo_sueldo` VARCHAR(10) NULL,\n"
                    + "  `estado` VARCHAR(20) NULL,\n"
                    + "  `LiquidacionEmpleado_idLiquidacionEmpleado` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idRECIBO_SUELDO`, `LiquidacionEmpleado_idLiquidacionEmpleado`),\n"
                    + "  CONSTRAINT `fk_RECIBO_SUELDO_LiquidacionEmpleado1`\n"
                    + "  FOREIGN KEY (`LiquidacionEmpleado_idLiquidacionEmpleado`)\n"
                    + "  REFERENCES `mydb`.`LiquidacionEmpleado` (`idLiquidacionEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            	/*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_RECIBO_SUELDO_LiquidacionEmpleado1_idx` "
            		+ "ON" + db +".`RECIBO_SUELDO` (`LiquidacionEmpleado_idLiquidacionEmpleado` ASC);");
            pst.execute();
            */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`RELACION__EMPLEADO_CONCEPTOS` (\n"
                    + " `unidad_concepto` VARCHAR(40) NULL,\n"
                    + "  `TIPO_LIQ_idTIPO_LIQ` INT NOT NULL,\n"
                    + "  `CONCEPTO_SUELDO_idCONCEPTO_SUELDO` INT NOT NULL,\n"
                    + "  `Empleado_idEmpleado` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`TIPO_LIQ_idTIPO_LIQ`, `CONCEPTO_SUELDO_idCONCEPTO_SUELDO`, `Empleado_idEmpleado`),\n"
                    + " CONSTRAINT `fk_RELACION__EMPLEADO_CONCEPTOS_TIPO_LIQ1`\n"
                    + "  FOREIGN KEY (`TIPO_LIQ_idTIPO_LIQ`) "
                    + "  REFERENCES `mydb`.`TIPO_LIQ` (`idTIPO_LIQ`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_RELACION__EMPLEADO_CONCEPTOS_CONCEPTO_SUELDO1`\n"
                    + "  FOREIGN KEY (`CONCEPTO_SUELDO_idCONCEPTO_SUELDO`)\n"
                    + "  REFERENCES `mydb`.`CONCEPTO_SUELDO` (`idCONCEPTO_SUELDO`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_RELACION__EMPLEADO_CONCEPTOS_Empleado1`\n"
                    + "  FOREIGN KEY (`Empleado_idEmpleado`)\n"
                    + "  REFERENCES `mydb`.`Empleado` (`idEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute(); 
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_RELACION__EMPLEADO_CONCEPTOS_CONCEPTO_SUELDO1_idx` "
            		+ "ON" + db +".`RELACION__EMPLEADO_CONCEPTOS` (`CONCEPTO_SUELDO_idCONCEPTO_SUELDO` ASC);");
            pst.execute();
           
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_RELACION__EMPLEADO_CONCEPTOS_Empleado1_idx` "
            		+ "ON" + db +".`RELACION__EMPLEADO_CONCEPTOS` (`Empleado_idEmpleado` ASC);");
            pst.execute();
            */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`OBRA_SOCIAL` (\n"
                    + "  `idOBRA_SOCIAL` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `nombre` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idOBRA_SOCIAL`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
                       
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`BANCO` (\n"
                    + "  `idBANCO` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `nombre` VARCHAR(45) NULL,\n"
                    + "   PRIMARY KEY (`idBANCO`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Acoplado` (\n"
                    + "  `idAcoplado` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Marca` VARCHAR(45) NULL,\n"
                    + "  `Patente` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idAcoplado`))\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Remito_has_Acoplado` (\n"
                    + "  `Remito_idRemito` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `Remito_Origen_Destino_idOrigen_Destino` INT NOT NULL,\n"
                    + "  `Acoplado_idAcoplado` INT NOT NULL,\n"
                    + "   PRIMARY KEY (`Remito_idRemito`, `Remito_Origen_Destino_idOrigen_Destino`, `Acoplado_idAcoplado`),\n"
                    + "  CONSTRAINT `fk_Remito_has_Acoplado_Remito1`\n"
                    + "  FOREIGN KEY (`Remito_idRemito` , `Remito_Origen_Destino_idOrigen_Destino`)\n"
                    + " REFERENCES `mydb`.`Remito` (`idRemito` , `Origen_Destino_idOrigen_Destino`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Remito_has_Acoplado_Acoplado1`\n"
                    + "  FOREIGN KEY (`Acoplado_idAcoplado`)\n"
                    + " REFERENCES `mydb`.`Acoplado` (`idAcoplado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Remito_has_Acoplado_Acoplado1_idx` "
            		+ "ON" + db +".`Remito_has_Acoplado` (`Acoplado_idAcoplado` ASC);");
            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Remito_has_Acoplado_Remito1_idx` "
            		+ "ON" + db +".`Remito_has_Acoplado` (`Remito_idRemito` ASC);");
            pst.execute();
            */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`RELACION_EMPLEADO_BANCO` (\n"
                    + "  `idRELACION_EMPLEADO_BANCO` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `BANCO_idBANCO` INT NOT NULL,\n"
                    + "  `Empleado_idEmpleado` INT NOT NULL,\n"
                    + "  `NroCuenta` VARCHAR(45) NULL,\n"
                    + "  PRIMARY KEY (`idRELACION_EMPLEADO_BANCO`, `BANCO_idBANCO`, `Empleado_idEmpleado`),\n"
                    + "  CONSTRAINT `fk_RELACION_EMPLEADO_BANCO_BANCO1`\n"
                    + " FOREIGN KEY (`BANCO_idBANCO`)\n"
                    + " REFERENCES `mydb`.`BANCO` (`idBANCO`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_RELACION_EMPLEADO_BANCO_Empleado1`\n"
                    + "  FOREIGN KEY (`Empleado_idEmpleado`)\n"
                    + " REFERENCES `mydb`.`Empleado` (`idEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_RELACION_EMPLEADO_BANCO_BANCO1_idx` "
            		+ "ON" + db +".`RELACION_EMPLEADO_BANCO` (`BANCO_idBANCO` ASC);");
            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_RELACION_EMPLEADO_BANCO_Empleado1_idx` "
            		+ "ON" + db +".`RELACION_EMPLEADO_BANCO` (`Empleado_idEmpleado` ASC);");
            pst.execute();
            */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`RELACION_EMPLEADO_OS` (\n"
                    + "  `idRELACION_EMPLEADO_OS` INT NOT NULL AUTO_INCREMENT,\n"
                    + "   `OBRA_SOCIAL_idOBRA_SOCIAL` INT NOT NULL,\n"
                    + "  `Empleado_idEmpleado` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idRELACION_EMPLEADO_OS`, `OBRA_SOCIAL_idOBRA_SOCIAL`, `Empleado_idEmpleado`),\n"
                    + "  CONSTRAINT `fk_RELACION_EMPLEADO_OS_OBRA_SOCIAL1`\n"
                    + "  FOREIGN KEY (`OBRA_SOCIAL_idOBRA_SOCIAL`)\n"
                    + " REFERENCES `mydb`.`OBRA_SOCIAL` (`idOBRA_SOCIAL`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_RELACION_EMPLEADO_OS_Empleado1`\n"
                    + "  FOREIGN KEY (`Empleado_idEmpleado`)\n"
                    + " REFERENCES `mydb`.`Empleado` (`idEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_RELACION_EMPLEADO_OS_OBRA_SOCIAL1_idx` "
            		+ "ON" + db +".`RELACION_EMPLEADO_OS` (`OBRA_SOCIAL_idOBRA_SOCIAL` ASC);");
            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_RELACION_EMPLEADO_OS_Empleado1_idx` "
            		+ "ON" + db +".`RELACION_EMPLEADO_OS` (`Empleado_idEmpleado` ASC);");
            pst.execute();
            
            */
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Familiar` (\n"
                    + "  `idFamiliar` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `Parentesco` VARCHAR(45) NULL,\n"
                    + "  `Persona_idPersona` INT NOT NULL,\n"
                    + "  `Empleado_idEmpleado` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idFamiliar`, `Persona_idPersona`, `Empleado_idEmpleado`),\n"
                    + "  CONSTRAINT `fk_Familiar_Persona1`\n"
                    + " FOREIGN KEY (`Persona_idPersona`)\n"
                    + " REFERENCES `mydb`.`Persona` (`idPersona`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_Familiar_Empleado1`\n"
                    + "  FOREIGN KEY (`Empleado_idEmpleado`)\n"
                    + " REFERENCES `mydb`.`Empleado` (`idEmpleado`)\n"
                    + "  ON DELETE NO ACTION\n"
                    + "  ON UPDATE NO ACTION)\n"
                    + "  ENGINE = InnoDB;");
            pst.execute();
            
            /*
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Familiar_Persona1_idx` "
            		+ "ON" + db +".`Familiar` (`Persona_idPersona` ASC);");
            pst.execute();
            pst = con.mkDataBase().prepareStatement(
            		"CREATE INDEX `fk_Familiar_Empleado1_idx` "
            		+ "ON" + db +".`Familiar` (`Empleado_idEmpleado` ASC);");
            pst.execute();
            
            pst = con.mkDataBase().prepareStatement(
            		"SET SQL_MODE=@OLD_SQL_MODE;\n"
            		+ "SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;\n"
            		+ "SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;\n ");
            pst.execute();
            
            */
            
            
            
            
            
            
            
            
            
            
            
            
            System.out.println("Create Database Sucessfuly");

        } catch (SQLException ex) {
            System.err.println(ex);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Server Configure");
                stage.showAndWait();
            } catch (IOException ex1) {
                Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
