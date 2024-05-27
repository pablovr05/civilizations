<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramId"/>
    <xsl:template match="unit">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Civilizations</title>
                <link rel="stylesheet" href="attack_unit.css"/>
            </head>
            <body>
                <div class="content">
                    <h1><xsl:value-of select="name"/></h1>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>