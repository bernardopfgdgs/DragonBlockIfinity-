#!/bin/bash

echo "=========================================="
echo "DIMENSÕES DOS ARQUIVOS PNG"
echo "=========================================="
echo ""

# Procura todos os arquivos PNG recursivamente
find src/main/resources/assets/dragonblockinfinity/textures -type f -name "*.png" 2>/dev/null | while read -r file; do
    # Usa file para tentar obter dimensões
    info=$(file "$file" 2>/dev/null)
    
    # Extrai nome do arquivo
    filename=$(basename "$file")
    
    # Extrai caminho relativo
    relpath=$(echo "$file" | sed 's|src/main/resources/assets/dragonblockinfinity/textures/||')
    
    # Tenta extrair dimensões do output do file
    if echo "$info" | grep -q "PNG image data"; then
        dimensions=$(echo "$info" | grep -oP '\d+\s*x\s*\d+' | head -1)
        echo "[$dimensions] $relpath"
    else
        echo "[UNKNOWN] $relpath"
    fi
done | sort

echo ""
echo "=========================================="
echo "Concluído!"
echo "=========================================="
