(ns culture.facts
  "Country-level regional-culture catalog for Kiribati (KIR) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"KIR"
   [{:culture/id "kir.dish.te-tuae"
     :culture/name "Te tuae"
     :culture/country "KIR"
     :culture/kind :dish
     :culture/summary "Dried pandanus cake, one of the traditional processed pandanus foods of Kiribati."
     :culture/url "https://en.wikipedia.org/wiki/Kiribati"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kir.beverage.karewe"
     :culture/name "Karewe"
     :culture/name-local "te karewe"
     :culture/country "KIR"
     :culture/kind :beverage
     :culture/summary "Freshly tapped coconut-spathe sap, drunk fresh as a refreshing drink in Kiribati (where palm toddy is called karewe); the fermented sap is used as an alcoholic beverage."
     :culture/url "https://en.wikipedia.org/wiki/Palm_wine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kir.product.copra"
     :culture/name "Copra"
     :culture/country "KIR"
     :culture/kind :product
     :culture/summary "Dried coconut flesh; Kiribati's leading export, accounting for about two-thirds of the country's export revenue."
     :culture/url "https://en.wikipedia.org/wiki/Economy_of_Kiribati"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kir.product.babai"
     :culture/name "Babai (giant swamp taro)"
     :culture/country "KIR"
     :culture/kind :product
     :culture/summary "Giant swamp taro (Cyrtosperma merkusii), called babai in Kiribati, grown in purpose-built swamp pits dug below the freshwater lens; its cultivation has deep cultural significance in Kiribati."
     :culture/url "https://en.wikipedia.org/wiki/Cyrtosperma_merkusii"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kir.product.te-kamaimai"
     :culture/name "Te kamaimai"
     :culture/country "KIR"
     :culture/kind :product
     :culture/summary "Coconut-sap syrup made from coconut sap, a traditional processed food of Kiribati."
     :culture/url "https://en.wikipedia.org/wiki/Kiribati"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kir.craft.maneaba"
     :culture/name "Maneaba"
     :culture/country "KIR"
     :culture/kind :craft
     :culture/summary "Traditional centre of tribal and national governance in Kiribati, built by the whole community from coconut wood, coral, coconut string and pandanus thatch, with each structural element carrying practical and symbolic significance."
     :culture/url "https://en.wikipedia.org/wiki/Maneaba"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kir.heritage.phoenix-islands-protected-area"
     :culture/name "Phoenix Islands Protected Area"
     :culture/country "KIR"
     :culture/kind :heritage
     :culture/summary "408,250 km2 marine protected area in Kiribati's central Pacific waters, designated a UNESCO World Heritage Site in 2010 as the largest and deepest World Heritage site in the world at inscription."
     :culture/url "https://en.wikipedia.org/wiki/Phoenix_Islands_Protected_Area"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "kir.heritage.kiribati-dance"
     :culture/name "Kiribati dance"
     :culture/country "KIR"
     :culture/kind :heritage
     :culture/summary "Traditional dance of Kiribati (styles include ruoia, kaimatoa, buki and tirere), distinguished among Pacific Islands dance by the dancer's outstretched arms and sudden birdlike head movements."
     :culture/url "https://en.wikipedia.org/wiki/Dance_in_Kiribati"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-kir culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "KIR"))
                 " KIR entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
