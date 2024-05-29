<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramId"/>
    <xsl:template match="/defense_units">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Civilizations</title>
                <link rel="stylesheet" href="defense_units.css"/>
                <script src="shadowPartial.js"></script>
            </head>
            <body>
                <div class="content">
                    <h1>Defense Units (Unidades de ataque)</h1>
                    <xsl:for-each select="unit">
                        <div class="hover-div">
                            <h1><xsl:value-of select="name"/></h1>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>