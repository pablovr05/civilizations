<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramId"/>
    <xsl:template match="/attack_units">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Civilizations</title>
                <link rel="stylesheet" href="attack_units.css"/>
            </head>
            <body>
                <div class="content">
                    <h1>Attack Units (Unidades de ataque)</h1>
                    <xsl:for-each select="unit">
                        <xsl:element name="a">
                            <xsl:attribute name="class">hover-a</xsl:attribute>
                            <xsl:attribute name="href"><xsl:value-of select="name"/>.html</xsl:attribute>
                            <xsl:value-of select="name"></xsl:value-of>
                        </xsl:element>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>