import pandas
import json
import numpy
import requests

LEXIQUE_URL = "http://www.lexique.org/databases/Lexique383/Lexique383.tsv"
LEXIQUE_SEPARATOR = "\t"

API_URL = "http://localhost:8080/api/v1/mot"

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


for i in range(len(lexique.index)):
    currentWord = Word()

    currentWord.add("ortho", str(lexique["ortho"][i]))
    currentWord.add("phon", str(lexique["phon"][i]))
    currentWord.add("cgram", str(lexique["cgram"][i]))
    currentWord.add("freqfilms2", float(lexique["freqfilms2"][i]))
    currentWord.add("nbphons", int(lexique["nbphons"][i]))
    currentWord.add("p_cvcv", str(lexique["p_cvcv"][i]))
    currentWord.add("nbsyll", int(lexique["nbsyll"][i]))

    if lexique["cgram"][i] == "NOM" and lexique["nombre"][i] == "s":
        currentWord.add("image", unidecode.unidecode(str(lexique["ortho"][i])))
    else:
        currentWord.add("image", unidecode.unidecode(str(lexique["lemme"][i])))

    currentRequest = requests.post(
        API_URL,
        headers = REQUEST_HEADER,
        data = currentWord.to_json())