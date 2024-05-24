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

# Directorios de entrada y salida
input_dir = './xml/'
output_dir = './html/'

# Asegurarse de que el directorio de salida exista
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

# Lista de archivos base (sin extensión)
files = ['attack_units', 'buildings', 'defences', 'special_units']

# Transformar cada archivo XML utilizando su archivo XSL correspondiente
for file in files:
    xml_path = os.path.join(input_dir, f"{file}.xml")
    xsl_path = os.path.join(input_dir, f"{file}.xsl")
    output_path = os.path.join(output_dir, f"{file}.html")

    # Verificar si ambos archivos existen antes de transformar
    if os.path.exists(xml_path) and os.path.exists(xsl_path):
        transform_xml_to_html(xml_path, xsl_path, output_path)
    else:
        print(f"Faltan {xml_path} o {xsl_path}")