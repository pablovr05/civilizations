<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramId"/>
    <xsl:template match="unit">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Civilizations</title>
                <link rel="stylesheet" href="defense_unit.css"/>
            </head>
            <body>
                <div class="content">
                    <h1><xsl:value-of select="name"/></h1>
                    <xsl:element name="img">
                        <xsl:attribute name="src">imagenes/<xsl:value-of select="sprite"/>.png</xsl:attribute>
                    </xsl:element>

                    <div class="container">
                        <span class="item">
                            <img src="imagenes/damage.png"/>
                            <span><xsl:value-of select="base_damage"/></span>
                        </span>
                        <span class="item">
                            <img src="imagenes/armor.png"/>
                            <span><xsl:value-of select="armour"/></span>
                        </span>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>