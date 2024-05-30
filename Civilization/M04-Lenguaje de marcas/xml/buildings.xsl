<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramId"/>
    <xsl:variable name="copyrightSymbol">&#169;</xsl:variable>
    <xsl:template match="/buildings">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Civilizations</title>
                <link rel="stylesheet" href="attack_units.css"/>
            </head>
            <body>
                <nav class="navbar">
                    <ul class="nav-list">
                        <li class="nav-item">
                            <a href="index.html" class="nav-link home-icon">
                                <img src="imagenes/logo.png" class="icon"></img>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="index.html" class="nav-link">Inicio</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle">Units and Buildings</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown-item"><a href="attack_units.html" class="dropdown-link">Attack Units</a></li>
                                <li class="dropdown-item"><a href="defense_units.html" class="dropdown-link">Defense Units</a></li>
                                <li class="dropdown-item"><a href="special_units.html" class="dropdown-link">Special Units</a></li>
                                <li class="dropdown-item"><a href="buildings.html" class="dropdown-link">Buildings</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a href="tutorial.html" class="nav-link">Tutorial</a>
                        </li>
                        <li class="nav-item">
                            <a href="aboutus.html" class="nav-link">Sobre Nosotros</a>
                        </li>
                    </ul>
                </nav>
            
                <script src="menu.js"></script>

                <div class="content">
                    <h1>Buildings (Edificios)</h1>
                    <xsl:for-each select="building">
                        <xsl:element name="a">
                            <xsl:attribute name="class">hover-a</xsl:attribute>
                            <xsl:attribute name="href"><xsl:value-of select="name"/>.html</xsl:attribute>
                            <xsl:value-of select="name"></xsl:value-of>
                        </xsl:element>
                    </xsl:for-each>
                    <a href="index.html" class="back-link">Volver Atrás</a>
                </div>
                <footer class="footer">
                    <div class="footer-content">
                        <p><xsl:value-of select="$copyrightSymbol"/> 2024 Civilizations. Todos los derechos reservados.</p>
                    </div>
                </footer>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>