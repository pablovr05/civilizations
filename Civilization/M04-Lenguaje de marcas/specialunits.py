from lxml import etree
import os

# Leer un archivo XML
def read_xml(path):
    with open(path, 'r', encoding='utf-8') as file:
        return bytes(bytearray(file.read(), encoding='utf-8'))

# Escribir un archivo HTML
def write_html(path, html):
    with open(path, 'w', encoding='utf-8') as file:
        file.write(html)

# Transformar un archivo XML a HTML usando XSLT
def transform_xml_to_html(xml_path, xsl_path, output_path):
    try:
        # Leer y parsear el archivo XML
        xml_data = read_xml(xml_path)
        xml_tree = etree.XML(xml_data)

        # Leer y parsear el archivo XSL
        xsl_data = read_xml(xsl_path)
        xsl_tree = etree.XML(xsl_data)

        # Crear la transformación XSLT
        transform = etree.XSLT(xsl_tree)

        # Aplicar la transformación
        html_dom = transform(xml_tree)
        html_result = etree.tostring(html_dom, pretty_print=True, method="html").decode('utf-8')

        # Escribir el resultado en un archivo HTML
        write_html(output_path, html_result)
        print(f"Transformación completa: {output_path}")
    except Exception as e:
        print(f"Error al transformar {xml_path}: {e}")

# Transformar cada unidad en un HTML individual
def transform_units_to_html(xml_path, xsl_path, output_dir):
    try:
        # Leer y parsear el archivo XML
        xml_data = read_xml(xml_path)
        xml_tree = etree.XML(xml_data)

        # Leer y parsear el archivo XSL
        xsl_data = read_xml(xsl_path)
        xsl_tree = etree.XML(xsl_data)

        # Crear la transformación XSLT
        transform = etree.XSLT(xsl_tree)

        # Aplicar la transformación a cada unidad y guardar el HTML individual
        units = xml_tree.xpath('//unit')
        for i, unit in enumerate(units):
            name_element = unit.find('name')
            if name_element is not None:
                unit_name = name_element.text.strip()
                print(f"Transformando unidad {unit_name}: {etree.tostring(unit, pretty_print=True).decode('utf-8')}")
                unit_html_dom = transform(unit)
                if unit_html_dom is not None:
                    unit_html_result = etree.tostring(unit_html_dom, pretty_print=True, method="html").decode('utf-8')
                    unit_output_path = os.path.join(output_dir, f"{unit_name}.html")
                    write_html(unit_output_path, unit_html_result)
                    print(f"Transformación completa para {unit_name}: {unit_output_path}")
                else:
                    print(f"Error al transformar la unidad {unit_name}: resultado None")
            else:
                print(f"Unidad {i+1} no tiene un nombre especificado.")
    except Exception as e:
        print(f"Error al transformar las unidades de {xml_path}: {e}")

def main():
    # Directorios de entrada y salida
    input_dir = './xml/'
    output_dir = './html/'

    # Asegurarse de que el directorio de salida exista
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # Archivos XML y XSL principales
    main_xml_file = 'special_units.xml'
    main_xsl_file = 'special_units.xsl'
    unit_xsl_file = 'special_unit.xsl'

    # Transformar el archivo XML principal
    xml_path = os.path.join(input_dir, main_xml_file)
    main_xsl_path = os.path.join(input_dir, main_xsl_file)
    output_path = os.path.join(output_dir, 'special_units.html')
    transform_xml_to_html(xml_path, main_xsl_path, output_path)

    # Transformar cada unidad en un archivo HTML individual
    unit_xsl_path = os.path.join(input_dir, unit_xsl_file)
    transform_units_to_html(xml_path, unit_xsl_path, output_dir)

if __name__ == "__main__":
    main()