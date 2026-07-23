# cloud-itonami-iso3166-kir

**KIR**: Republic of Kiribati.

- Central Procurement Unit (CPU, a division of the Ministry of Finance
  and Economic Development) public-procurement compliance -- Public
  Procurement Act 2019 (s.17 Central Contract Award Board / s.18
  Contract Award Committee); Public Procurement Manual 2020's own
  four-tier AUD threshold ladder (VLVP/LVP/MVP/HVP)
- Ministry of Tourism, Commerce, Industry and Cooperatives (MTCIC)
  business/company registration (Companies Ordinance Cap10A) + Foreign
  Investment Act 2018 (Reserved/Restricted/Prohibited activities) +
  Kiribati Tax Division TIN registration

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as every other `cloud-itonami-iso3166-*` sibling:

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Central
  Procurement Unit (Public Procurement Act 2019, Regulations 2020,
  Manual 2020), the Ministry of Tourism, Commerce, Industry and
  Cooperatives (Companies Ordinance Cap10A, Foreign Investment Act
  2018), and the Kiribati Tax Division. `governor.cljc` runs SEVEN hard
  checks; the FLAGSHIP check independently recomputes the Public
  Procurement Manual 2020's own §7.1.6 four-tier AUD threshold
  (VLVP<1,000 / LVP 1,000-9,999 / MVP 10,000-49,999 / HVP>=50,000),
  including the Manual's own explicit rule that a recurring engagement's
  value accumulates over its full duration (its own worked example: AUD
  4,000/month x 18 months = AUD 72,000, therefore HVP) -- a filing that
  declares a lower-oversight tier than its own accumulated value
  actually requires is HARD-held. A SECOND, independently-researched
  check enforces the Foreign Investment Act 2018's own subsidiary
  Foreign Investment Guideline, Schedule 1 (Reserved List): an absolute
  bar on foreign investors engaging in activities reserved for Kiribati
  citizens. See the namespace docstrings for the full research trail
  and honestly-narrowed scope, including facts this iteration could NOT
  verify (e.g. the VAT Act's own specific title/year, or the Companies
  Ordinance's own full primary statutory text).
- `src/statute/facts.cljc` -- general-law catalog: the Companies
  Ordinance (Cap 10A), the Foreign Investment Act 2018, the Employment
  and Industrial Relation Code 2015 (labour law, with section-level
  detail on minimum wage, hours and rest periods), the Occupational
  Health & Safety Act 2015, and the Income Tax Ordinance (title-only,
  an honest gap -- see the namespace docstring).

Every citation is curl/pdftotext/tesseract-OCR-verified against an
official source (`procurement.gov.ki`, `mfed.gov.ki`, `mtcic.gov.ki`,
`tax.gov.ki`, `employment.gov.ki`); where the LIVE official site could
not be reached this session (`legal.gov.ki` -- DNS/SSL failure; live
`paclii.org` -- Cloudflare bot-detection challenge, not bypassed), the
SAME official page was instead read from a `web.archive.org` snapshot,
or the gap is disclosed honestly with no citation invented -- see
`marketentry.facts`'s docstring for exactly which facts are
live-verified vs. archived-snapshot-verified vs. an honestly-flagged
gap.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Kiribati:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
