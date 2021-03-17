import pandas
import json
import numpy
import requests
import unidecode

LEXIQUE_URL = "http://www.lexique.org/databases/Lexique383/Lexique383.tsv"
LEXIQUE_SEPARATOR = "\t"

API_URL_IMAGE = "http://localhost:8080/api/v1/image"

ARASAAC_API_URL_BEST_SEARCH_FR = "https://api.arasaac.org/api/pictograms/fr/bestsearch/" 
ARASAAC_API_URL_PICTOGRAMS = "https://api.arasaac.org/api/pictograms/"

lexique = pandas.read_csv(LEXIQUE_URL, sep = LEXIQUE_SEPARATOR)

all_image_name = []

for i in range(len(lexique.index)):
    current_word = ""
    if lexique["cgram"][i] == "NOM" and lexique["nombre"][i] == "s":
        current_word = lexique["ortho"][i]
    else:
        current_word = lexique["lemme"][i]
    
    current_word = str(current_word)
    if current_word in all_image_name:
        continue

    
    all_image_name.append(current_word)

    bestSearchResponse = requests.get(ARASAAC_API_URL_BEST_SEARCH_FR + current_word)
    if bestSearchResponse.status_code != 200:
        continue
    
    current_word = unidecode.unidecode(current_word)
    image_id = bestSearchResponse.json()[0]["_id"]

    pictogramsResponse = requests.get(ARASAAC_API_URL_PICTOGRAMS + str(image_id))
    if pictogramsResponse.status_code != 200:
        continue

    f = (current_word + ".png", pictogramsResponse.content, "multipart/form-data", {'Expires': '0'})

    r = requests.post(
        API_URL_IMAGE,
        files = {"file": f}
        )
