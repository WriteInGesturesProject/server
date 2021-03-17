import pandas
import json
import numpy
import requests
import unidecode

LEXIQUE_URL = "http://www.lexique.org/databases/Lexique383/Lexique383.tsv"
LEXIQUE_SEPARATOR = "\t"

COLUMN_NAME = ["ortho", "phon", "cgram", "freqfilms2", "nbphons", "p_cvcv", "nbsyll"]


API_URL_MOT = "http://localhost:8080/api/v1/mot"
API_URL_IMAGE = "http://localhost:8080/api/v1/image"

ARASAAC_API_URL_BEST_SEARCH_FR = "https://api.arasaac.org/api/pictograms/fr/bestsearch/" 
ARASAAC_API_URL_PICTOGRAMS = "https://api.arasaac.org/api/pictograms/"

REQUEST_HEADER = {'Content-type':'application/json', 'Accept':'application/json'}

lexique = pandas.read_csv(LEXIQUE_URL, sep = LEXIQUE_SEPARATOR)

class Word:
    def __init__(self):
        self.word = {}

    def add(self, key, value):
        self.word[key] = value

    def to_string(self):
        result = ""
        for key in self.word.keys():
            result += key + ": " + str(self.word[key]) + "\n"    
        return result
    
    def to_json(self):
        return json.dumps(self.word, indent = 1)

allImageName = []

for i in range(0, len(lexique.index)):
    currentImageName = ""
    currentWord = Word()

    currentWord.add("ortho", str(lexique["ortho"][i]))
    currentWord.add("phon", str(lexique["phon"][i]))
    currentWord.add("cgram", str(lexique["cgram"][i]))
    currentWord.add("freqfilms2", float(lexique["freqfilms2"][i]))
    currentWord.add("nbphons", int(lexique["nbphons"][i]))
    currentWord.add("p_cvcv", str(lexique["p_cvcv"][i]))
    currentWord.add("nbsyll", int(lexique["nbsyll"][i]))
    
    if lexique["cgram"][i] == "NOM" and lexique["nombre"][i] == "s":
        currentImageName = str(lexique["ortho"][i])
    else:
        currentImageName = str(lexique["lemme"][i])

    currentWord.add("image", unidecode.unidecode(currentImageName))

    currentRequest = requests.post(
        API_URL_MOT,
        headers = REQUEST_HEADER,
        data = currentWord.to_json())
    
    if currentRequest.status_code != 200:
        print("[Erreur] post mot: " + currentWord.word["ortho"] + " | i: " + str(i))
        break

    if currentImageName in allImageName:
        continue

    allImageName.append(currentImageName)

    bestSearchResponse = requests.get(ARASAAC_API_URL_BEST_SEARCH_FR + currentImageName)
    if bestSearchResponse.status_code != 200:
        continue
       
    currentImageName = unidecode.unidecode(currentImageName)

    imageId = bestSearchResponse.json()[0]["_id"]

    pictogramsResponse = requests.get(ARASAAC_API_URL_PICTOGRAMS + str(imageId))
    if pictogramsResponse.status_code != 200:
        continue

    f = (currentImageName + ".png", pictogramsResponse.content, "multipart/form-data", {'Expires': '0'})

    r = requests.post(
        API_URL_IMAGE,
        files = {"file": f}
        )


    

    
