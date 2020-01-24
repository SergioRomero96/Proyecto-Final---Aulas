/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TIEMPO EN JUJUY
 */
public class ListMaterias implements Serializable {
    private List<String> materias;
    
    public ListMaterias() {
        materias = new ArrayList<>();
        
        /*Materias en Común - Ingenierías - Licenciaturas - PreGrado*/
        materias.add("Álgebra y Geometría Analítica");
        materias.add("Análisis Matemático");
        materias.add("Análisis Matemático I");
        materias.add("Análisis Matemático II");
        materias.add("Física General");
        materias.add("Física I");
        materias.add("Física II");
        materias.add("Introducción a la Informática");
        materias.add("Introducción a la Geología");
        materias.add("Introducción a la Minería");
        materias.add("Introducción a las Perforaciones Geológicas");
        materias.add("Química I");
        materias.add("Química II");
        materias.add("Fisicoquímica");
        materias.add("Mineralogía");
        materias.add("Estática y Resistencia de Materiales");
        materias.add("Electricidad y Electrónica");
        materias.add("Carteo Geológico");
        materias.add("Petrología Ígnea y Metamórfica");
        materias.add("Geofísica");
        materias.add("Higiene y Seguridad Ambiental y del Trabajo");
        materias.add("Sistemas de Representación");
        materias.add("Nivel de Suficiencia de Inglés");
        materias.add("Nivel de Aptitud de Inglés");
        materias.add("Práctica Profesional Supervisada");
        materias.add("Práctica Profesional Asistida");
        materias.add("Proyecto Final");
        materias.add("Formulación y Evaluación de Proyectos");
        
        /* Química */
        materias.add("Economía, Organización y Dirección de Empresas");
        materias.add("Operaciones Unitarias I");
        materias.add("Ingeniería de las Reacciones Químicas");
        materias.add("Instrumentación y Control de Procesos");
        materias.add("Operaciones Unitarias II");
        materias.add("Ingeniería de los Servicios");
        materias.add("Materiales para Ingeniería");
        materias.add("Tecnología Mecánica");
        materias.add("Bioingeniería");
        materias.add("Procesos Electroquímicos");
        materias.add("Ingeniería de Procesos");
        materias.add("Simulación y Optimización");

        /* Minas */
        materias.add("Economía Minera y Dirección y Organización de Empresas");
        materias.add("Petrografía y Petrología");
        materias.add("Legislación Minera y Ética Profesional");         
        materias.add("Metalurgia Extractiva III");
        materias.add("Ingeniería de la Producción y de la Empresa");
        materias.add("Prospección y Exploración");

        /* Procesamiento de Minerales */
        materias.add("Beneficio de los Minerales");
        materias.add("Procesos de Metalurgia Extractiva");

        /* Minas - Procesamiento de Minerales */
        materias.add("Química Analítica y Análisis de Menas");
        materias.add("Procesamiento de Minerales I");
        materias.add("Procesamiento de Minerales II");
        materias.add("Procesamiento de Minerales III");
        materias.add("Metalurgia Extractiva I");
        materias.add("Metalurgia Extractiva II");

        /* Explotación en Minas */
        materias.add("Uso de Explosivo - Diseño de Voladuras");
        materias.add("Planificación de Explotación Minera");

        /* Minas - Explotacion en Minas */
        materias.add("Geología");
        materias.add("Topografía");
        materias.add("Máquinas Mineras y Servicios");
        materias.add("Mecánica de Rocas y Suelos");
        materias.add("Mecánica Aplicada a la Minería");
        materias.add("Explotación de Minas I");
        materias.add("Explotación de Minas II");
        materias.add("Yacimientos de Minerales");
        materias.add("Construcciones Mineras");

        /* Minas - Explotación en Minas - Procesamiento de Minerales */
        materias.add("Ingeniería de la Calidad y del Medio Ambiente");

        /* Minas - Química - Procesamiento de Minerales */
        materias.add("Termodinámica");

        /* Industrial */
        materias.add("Probabilidad y Estadística");
        materias.add("Termodinámica y Máquinas Térmicas");
        materias.add("Costos Industriales");
        materias.add("Economía y Dirección de Empresas");
        materias.add("Electrotecnia");
        materias.add("Organización de la Producción");
        materias.add("Ingeniería de Materiales");
        materias.add("Organización de Empresas");
        materias.add("Planeamiento y Control de la Producción");
        materias.add("Sistemas de Información");
        materias.add("Instalaciones y Control");
        materias.add("Mecánica de los Fluidos");
        materias.add("Ingeniería Legal");
        materias.add("Mecánica y Mecanismos");
        materias.add("Gestión Empresaria de la Calidad");
        materias.add("Operaciones Industriales");

        /* Informática */
        materias.add("Tecnología de la Información de las Comunicaciones");
        materias.add("Metodología de la Programación");
        materias.add("Desarrollo Sistemático de Programas");
        materias.add("Economía, Organización y Administración de Empresas");
        materias.add("Arquitectura de Redes de Computadoras");
        materias.add("Laboratorio de Computadoras");        
        materias.add("Sistemas de Información");
        materias.add("Métodos de Simulación");
        materias.add("Ingeniería de Software I");
        materias.add("Ingeniería de Software II");        

        /* Sistemas */
        materias.add("Programación I");
        materias.add("Programación II");
        materias.add("Programación III");
        materias.add("Redes y Comunicaciones");
        materias.add("Organización y Administración de Empresas");
        materias.add("Sistemas de Información I");
        materias.add("Sistemas de Información II");
        materias.add("Seguridad en Sistemas");
        materias.add("Taller de Metodología de la Investigación Científica");
        materias.add("Sistemas de Información Administrativa");
        materias.add("Aplicaciones de Bases de Datos I");
        materias.add("Aplicaciones de Bases de Datos II");
        materias.add("Gestión Ambiental");
        materias.add("Taller de Formación Profesional");
        materias.add("Sistemas Operativos Distribuidos");
        materias.add("Trabajo Final de Sistemas");

        /* Analista Programador Universitario */
        materias.add("Inglés I");
        materias.add("Inglés II");
        materias.add("Inglés III");
        materias.add("Inglés IV");
        materias.add("Inglés V");
        materias.add("Inglés VI");
        materias.add("Herramientas Informáticas I");
        materias.add("Herramientas Informáticas II");
        materias.add("Programación Estructurada");
        materias.add("Laboratorio de Sistemas Operativos I");
        materias.add("Laboratorio de Sistemas Operativos II");
        materias.add("Base de Datos I");
        materias.add("Base de Datos II");
        materias.add("Álgebra I");
        materias.add("Álgebra II");
        materias.add("Programación Visual");
        materias.add("Programación Concurrente y Paralela");
        materias.add("Programación Orientada a Objetos");
        materias.add("Análisis y Diseño de Sistemas I");
        materias.add("Análisis y Diseño de Sistemas II");
        materias.add("Redes I");
        materias.add("Redes II");
        materias.add("Programación y Servicios Web");
        materias.add("Laboratorio de Programación Orientado a Objetos I");
        materias.add("Laboratorio de Programación Orientado a Objetos II");
        materias.add("Herramientas Informáticas Avanzadas");
        materias.add("Legislación y Ejercico Profesional");

        /* Informática - Sistemas - APU */
        materias.add("Estructura de Datos");

        /* Informática - Sistemas */
        materias.add("Técnicas y Estructuras Digitales");
        materias.add("Probabilidades y Estadística");
        materias.add("Lógica Computacional");
        materias.add("Matemática Discreta");
        materias.add("Sistemas Operativos");
        materias.add("Bases de Datos");
        materias.add("Lenguajes Formales");
        materias.add("Modelos de Desarrollo de Programas y Programación Concurrente");
        materias.add("Compiladores");
        materias.add("Diseño de Sistemas Operativos");
        materias.add("Inteligencia Artificial");
        materias.add("Arquitectura de Computadoras");
        materias.add("Ingeniería del Conocimiento");
        materias.add("Legislación");

        /* Alimentos */
        materias.add("Estadística");
        materias.add("Biología General y Celular");
        materias.add("Matemática y Computación");
        materias.add("Bioquímica de los Alimentos");
        materias.add("Microbiología General y de los Alimentos");
        materias.add("Química Analítica y Análisis de los Alimentos");
        materias.add("Operaciones Básicas de los Alimentos");
        materias.add("Materiales y Equipos");
        materias.add("Tecnología de los Alimentos I");
        materias.add("Tecnología de los Alimentos II");
        materias.add("Calidad de los Alimentos");
        materias.add("Higiene y Seguridad Ambiental");

        /* Geológicas */
        materias.add("Geología de los Recursos Hídricos");
        materias.add("Geología Ambiental");
        materias.add("Suelos");
        materias.add("Geología de los Recursos Energéticos");
        materias.add("Geotecnia");
        materias.add("Geología de los Recursos Mineros");
        materias.add("Geología Regional");
        materias.add("Geología Económica de Proyectos");
        materias.add("Geología Legal");
        materias.add("Trabajo Final");

        /* Perforaciones */
        materias.add("Perforaciones I");
        materias.add("Perforaciones II");
        materias.add("Máquinas de Exploración");
        materias.add("Recursos Energéticos");
        materias.add("Petrografía Sedimentaria");
        materias.add("Legislación Minera y Laboral");
        materias.add("Recursos Hídricos");

        /* Perforaciones - Geológicas */
        materias.add("Yacimientos Minerales");

        /* Ciencias de la Tierra - Geológicas - Perforaciones */
        materias.add("Geología Estructural");
        
        /* Ciencias de la Tierra */
        materias.add("Práctica Geológica Asistida");

        /* Petroleo */
        materias.add("Geología del Petróleo");
        materias.add("Prospección y Perforación Petrolera");
        materias.add("Reservorios");

        /* Ciencias de la Tierra - Geológicas - Petroleo */
        materias.add("Práctica Geológica I");
        materias.add("Práctica Geológica II");
        materias.add("Geomorfología");
        materias.add("Sedimentología");
        materias.add("Teledetección y Sensores Remotos");
        materias.add("Estratigrafía y Geología Histórica");

        /* Ciencias de la Tierra - Geológicas */
        materias.add("Estadística Descrptiva y Probabilidades");
        materias.add("Paleontología");
        materias.add("Geoquímica");
        
        /* Geológicas - Química - Ciencias de la Tierra */
        materias.add("Química Analítica");

        /* Alimentos - Química */
        materias.add("Química Orgánica");
        materias.add("Fenómenos de Transporte");       

        /* Industrial - Informática - Sistemas */
        materias.add("Investigación Operativa");

        /* Informática - Sistemas - Minas */
        materias.add("Cálculo Numérico");

        /* Química - Minas */
        materias.add("Matemática para Ingenieros");
        
        /* Química - Industrial */
        materias.add("Programación Aplicada");
    }

    public ListMaterias(List<String> materias) {
        this.materias = materias;
    }
    
    public List<String> getMaterias() {
        return materias;
    }

    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }
    
    
}
