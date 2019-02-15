# -*-coding:utf-8 -*-
import requests
from bs4 import BeautifulSoup
import re


class Spider:
    def __init__(self, domainUrl, urlPrefix, urlSuffix, startPage, endPage):
        self.domainUrl = domainUrl
        self.urlPrefix = urlPrefix
        self.urlSuffix = urlSuffix
        self.startPage = startPage
        self.endPage = endPage
        self.headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"}

    def run(self):
        for i in range(self.startPage, self.endPage + 1):
            self.requestListPage(self.urlPrefix + str(i) + self.urlSuffix)

    def requestListPage(self, url):
        response = requests.get(url, headers=self.headers)
        response.encoding = 'utf-8'
        soup = BeautifulSoup(response.text, features="html.parser")
        for dom in soup.select("body > div.w960.center.clear.mt1 > div.pleft > div.listbox > ul  a.preview"):
            self.requestDetailPage(self.domainUrl + dom.attrs["href"])

    def requestDetailPage(self, url):
        response = requests.get(url, headers=self.headers)
        response.encoding = "utf-8"
        beautifulSoup = BeautifulSoup(response.text, features="html.parser")
        parsedHtmlStr = str(beautifulSoup.select(
            "body > div.w960.center.clear.mt1 > div.pleft > div.viewbox > div.content")[0]).replace(
            '<div class="content">\n', "").replace("</div>", "").replace('src="/', 'src="' + self.domainUrl + "/")
        title = beautifulSoup.select("body > div.w960.center.clear.mt1 > div.pleft > div.viewbox > div.title > h2")[
            0].string
        summary = re.compile('<[^>]+>', re.S).sub('', parsedHtmlStr).replace('\r\n', '').replace('\t', '').replace(' ',
                                                                                                                   '')[
                  0:200]
        self.saveDetail(parsedHtmlStr, summary, title)

    def saveDetail(self, html, summary, title):
        response = requests.post("http://localhost:8080/add", headers=self.headers,
                                 data={"title": title, "content": html, "typeId": 32, "keyword": "Web前端",
                                       "summary": summary})
        print(response)


if __name__ == "__main__":
    Spider("http://www.java1234.com", "http://www.java1234.com/a/javabook/webbase/list_69_", ".html", 1, 8, ).run()
