(ns statute.facts
  "General-law compliance catalog for the Republic of Kiribati (KIR) --
  extends this repo's existing `marketentry.facts` (public-procurement
  market-entry only, narrow scope) with a second, orthogonal catalog of
  statutes a company operating in this jurisdiction must generally track
  for compliance. Mirrors cloud-itonami-iso3166-jpn/-deu/-bgr/-aze/-alb/
  -arm/-atg/-ben/-btn/-bwa/-caf/-est/-fji/-nru/-wsm's `statute.facts`
  (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL government-hosted URL (or, where the live
  official page could not be reached this session, a `web.archive.org`
  snapshot of that SAME official page, disclosed as such) that this
  iteration actually fetched and read this session -- never fabricated.

  - Companies/commercial-entity law: the **Companies Ordinance (Cap
    10A)**, confirmed directly from the Ministry of Tourism, Commerce,
    Industry and Cooperatives' (MTCIC) own Business and Companies
    Regulatory Division (BCRD) page (`mtcic.gov.ki/business-and-
    companies-regulatory-division-bcrd/`, fetched directly this session,
    live, HTTP 200), own \"Laws Administered\" list, quoted directly:
    \"Companies Ordinance (Cap10A), Registration of Business Names Act
    1988, Moneylenders' Act 1988.\" The SAME page publishes a fee schedule
    citing specific section numbers currently in force, quoted directly:
    \"Section 6 of CAP10A\" (register-inspection fees), \"Section 9 of
    CAP10A\" (authorized-share-capital registration fee ladder), \"Section
    14 of CAP10A\" (referencing s.14(3)/(4), company-name reservation/
    change fees), \"Section 24 of CAP10A\" (Register of Members inspection
    fees), \"Section 43 of CAP10A\" (referencing s.42, registration of
    resolutions). PacLII's own \"Kiribati Consolidated Legislation\" index
    independently cross-confirms a \"Companies Ordinance\" title exists
    (`paclii.org/ki/legis/consol_act/toc-C.html`, read via a
    `web.archive.org` snapshot dated 20241217151223 -- live `paclii.org`
    returned a Cloudflare \"Just a moment\" bot-detection challenge this
    session, NOT bypassed). This iteration did NOT independently fetch
    the Companies Ordinance's own full primary statutory text, only
    MTCIC's own fee-schedule citations of specific section numbers -- an
    honest limit on how deep this iteration went. **CURRENCY CAVEAT**:
    the SAME BCRD page states it is \"engaging with PSDI in reviewing the
    Companies Ordinance (Cap10a)\" and attaches a CONSULTATION-stage
    \"Companies Bill 2019\" draft -- NOT yet enacted, so NOT cited here as
    current law, only noted as a pending reform this catalog does not
    get ahead of.
  - Foreign investment: the **Foreign Investment Act 2018**, confirmed
    directly from MTCIC's own Investment Promotion Division page
    (`mtcic.gov.ki/investment-promotion-office/`, fetched directly this
    session, live, HTTP 200), own text quoted directly: \"as required by
    the foreign investment act 2018 and subsidiary legislations.\" This
    iteration did NOT independently fetch the Act's own full primary
    statutory text (a 23-page scanned PDF this session did not OCR), but
    DID independently fetch and read IN FULL, text-native, the Act's own
    subsidiary Foreign Investment Guideline (Schedules 1-3: Reserved/
    Restricted/Prohibited activities -- see `marketentry.facts` and
    `marketentry.registry` for the full research trail; this vertical's
    SECOND, independently-researched check, `reserved-activity-
    violation?`, is grounded here).
  - Employment law: the **Employment and Industrial Relation Code 2015**
    (\"EIRC 2015\"), confirmed directly from the Ministry of Employment
    and Human Resources' own Work Relations (WR) Unit page
    (`employment.gov.ki/labour-division/work-relations-wr-unit/
    employment-and-industrial-relation-code-2015-pg1`, fetched directly
    this session, live, HTTP 200 -- own bilingual English/Gilbertese
    content), own text quoted directly with SPECIFIC section numbers:
    \"In the EIRC2015 Part VII Section 75, Employers are bound to provide
    Contract of Employment and signed by both parties\"; \"refer to
    Section 67 of the EIRC2015\" for collective-agreement form/content;
    Part V (Minimum Wage Setting): \"an employer should pay the employee
    no less than the Minimum Wage. Private sectors/Private construction
    $1.30 per hour. Foreign funded project & construction. $3.00 per
    hour\"; \"In Section 83 of the EIRC2015, an employer shall not
    request an employee to work more than 8 hours a day or 40 hours per
    week\"; \"In Section 84 of the EIRC2015, every full-time employee
    shall be entitled to a daily rest period of at least 12 consecutive
    hours; and a weekly rest period of at least 48 consecutive hours\";
    \"In Section 85, Part IX of the EIRC 2015\", after 6 consecutive
    working hours an employee is entitled to a 1-hour meal break or two
    shorter tea breaks.
  - Workplace health and safety: the **Occupational Health & Safety Act
    2015** (\"OHS2015\"), confirmed directly from the SAME Ministry of
    Employment and Human Resources' own Occupational Health and Safety
    (OSH) Unit page (`employment.gov.ki/labour-division/occupational-
    health-safety-osh-unit/occupational-health-safety-act-2015`, fetched
    directly this session, live, HTTP 200), own page title and repeated
    in-page text consistently \"Occupational Health & Safety Act 2015\";
    own text on inspector powers (entry at reasonable hours, document
    production, testing/sampling, work-stoppage orders for immediate
    danger) and employer duties (safe working environment, no cost-
    shifting of safety equipment to employees, record retention \"for at
    least 5 years\"). **HONEST NOTE**: this Ministry's own site elsewhere
    labels the SAME Act's navigation link \"OHS Act, 2016\" -- a minor
    inconsistency on the Ministry's own site this iteration discloses
    rather than silently resolves; the dedicated Act page's own `<title>`
    and repeated body text both consistently say 2015, which this entry
    follows.
  - Tax: this iteration confirmed the **Kiribati Tax Division** (within
    MFED) currently administers income tax and value-added tax (VAT)
    directly from `tax.gov.ki` (\"Kiribati Tax\", fetched directly this
    session, live, HTTP 200): \"the tax system includes income tax for
    individuals and businesses and value-added tax (VAT) on goods and
    services\"; VAT returns due 15 days after each quarter-end, monthly
    withholding (including PAYE) due 21 days after each month-end.
    PacLII's own \"Kiribati Consolidated Legislation\" index (via
    `web.archive.org`, live `paclii.org` Cloudflare-blocked as above)
    confirms the TITLE \"Income Tax Ordinance\" exists
    (`toc-I.html`, snapshot dated 20241217151226) -- no year or section
    independently confirmed. **HONEST GAP**: this iteration could NOT
    independently confirm the VAT Act's own title/year/section at all --
    `tax.gov.ki` names it only generically and states \"Tax Acts &
    Regulations are available from the OAG (Office of the Attorney
    General)\"; the OAG's own site, `legal.gov.ki`, was UNREACHABLE this
    session (`curl` exit code 6, DNS resolution failure for the bare
    domain; `curl` exit code 35, SSL handshake error, for
    `www.legal.gov.ki`); no PacLII index entry beginning with \"Value
    Added\" or \"Goods and Services\" was found under either the V or G
    letters (`toc-V.html` has no Wayback snapshot at all; `toc-G.html`'s
    2017 snapshot lists only Gaming and Lotteries / Gilbert Islands
    Provident Fund / Government Borrowing and Guarantee Ordinances). This
    catalog does NOT include a separate VAT entry to paper over that
    gap -- the Income Tax Ordinance entry below is deliberately
    title-only, the SAME honest discipline this family's Nauru catalog
    uses for its own title-only labor entry.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit. KIR's Income Tax Ordinance
  entry is deliberately title-only (see namespace docstring) -- an
  honest scope limit, not an omission by design."
  {"KIR"
   [{:statute/id "kir.companies-ordinance-cap10a"
     :statute/title "Companies Ordinance (Cap 10A)"
     :statute/jurisdiction "KIR"
     :statute/kind :law
     :statute/law-number "Companies Ordinance (Cap10A), administered by the Business and Companies Regulatory Division (BCRD), Ministry of Tourism, Commerce, Industry and Cooperatives (MTCIC), per MTCIC's own official page (mtcic.gov.ki/business-and-companies-regulatory-division-bcrd/, fetched directly this session): fee schedule citing Section 6 (register inspection), Section 9 (authorized-share-capital fee ladder), Section 14 (name reservation/change, referencing s.14(3)/(4)), Section 24 (Register of Members inspection), Section 43 (referencing s.42, registration of resolutions). Cross-confirmed by PacLII's own Kiribati Consolidated Legislation index (via web.archive.org, live paclii.org Cloudflare-blocked). This iteration did not independently fetch the Ordinance's own full primary statutory text, only MTCIC's own fee-schedule section citations. CURRENCY CAVEAT: MTCIC's own page discloses active PSDI-supported review of this Ordinance, with a consultation-stage 'Companies Bill 2019' draft attached -- not yet enacted."
     :statute/url "https://mtcic.gov.ki/business-and-companies-regulatory-division-bcrd/"
     :statute/url-provenance :official-mtcic-gov-ki
     :statute/enacted-date nil
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "kir.foreign-investment-act-2018"
     :statute/title "Foreign Investment Act 2018"
     :statute/jurisdiction "KIR"
     :statute/kind :law
     :statute/law-number "Foreign Investment Act 2018, per MTCIC's own Investment Promotion Division page (mtcic.gov.ki/investment-promotion-office/, fetched directly this session): 'as required by the foreign investment act 2018 and subsidiary legislations'. This iteration did not independently fetch the Act's own full primary statutory text (a scanned PDF not OCR'd this session), but did independently fetch and read in full, text-native, the Act's own subsidiary Foreign Investment Guideline (Schedules 1-3: Reserved/Restricted/Prohibited activities), which grounds this vertical's own reserved-activity-violation? governor check -- see marketentry.facts and marketentry.registry."
     :statute/url "https://mtcic.gov.ki/investment-promotion-office/"
     :statute/url-provenance :official-mtcic-gov-ki
     :statute/enacted-date "2018-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:foreign-investment}}
    {:statute/id "kir.employment-and-industrial-relation-code-2015"
     :statute/title "Employment and Industrial Relation Code 2015 (EIRC 2015)"
     :statute/jurisdiction "KIR"
     :statute/kind :law
     :statute/law-number "Employment and Industrial Relation Code 2015, per the Ministry of Employment and Human Resources' own Work Relations (WR) Unit page (employment.gov.ki/labour-division/work-relations-wr-unit/employment-and-industrial-relation-code-2015-pg1, fetched directly this session, own text): Part VII s.75 (Contract of Employment), s.67 (collective agreement), Part V (Minimum Wage: private sector $1.30/hour; foreign-funded project & construction $3.00/hour), Part IX s.83 (max ordinary hours 8/day or 40/week), s.84 (rest periods: daily >= 12 consecutive hours, weekly >= 48 consecutive hours), s.85 (meal/tea breaks after 6 consecutive working hours)."
     :statute/url "https://www.employment.gov.ki/labour-division/work-relations-wr-unit/employment-and-industrial-relation-code-2015-pg1"
     :statute/url-provenance :official-employment-gov-ki
     :statute/enacted-date "2015-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor}}
    {:statute/id "kir.occupational-health-and-safety-act-2015"
     :statute/title "Occupational Health & Safety Act 2015 (OHS 2015)"
     :statute/jurisdiction "KIR"
     :statute/kind :law
     :statute/law-number "Occupational Health & Safety Act 2015, per the Ministry of Employment and Human Resources' own Occupational Health and Safety (OSH) Unit page (employment.gov.ki/labour-division/occupational-health-safety-osh-unit/occupational-health-safety-act-2015, fetched directly this session, own text): inspector powers (entry, document production, testing, work-stoppage orders); employer duties (safe workplace, no cost-shifting of safety equipment to employees, >= 5-year record retention). HONEST NOTE: the same Ministry site elsewhere labels this Act's nav link 'OHS Act, 2016' -- a minor own-site inconsistency disclosed rather than silently resolved; the dedicated Act page's own title and body text consistently say 2015, which this entry follows."
     :statute/url "https://www.employment.gov.ki/labour-division/occupational-health-safety-osh-unit/occupational-health-safety-act-2015"
     :statute/url-provenance :official-employment-gov-ki
     :statute/enacted-date "2015-01-01"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor :workplace-safety}}
    {:statute/id "kir.income-tax-ordinance"
     :statute/title "Income Tax Ordinance (title/year confirmed only -- see namespace docstring's honest gap)"
     :statute/jurisdiction "KIR"
     :statute/kind :law
     :statute/law-number "Income Tax Ordinance -- title confirmed via PacLII's own Kiribati Consolidated Legislation index (paclii.org/ki/legis/consol_act/toc-I.html, read via a web.archive.org snapshot dated 20241217151226; live paclii.org returned a Cloudflare bot-detection challenge this session, not bypassed). No year or section independently confirmed. tax.gov.ki (Kiribati Tax Division, MFED) independently confirms income tax and VAT are currently administered, but names them only generically and directs enquirers to the Office of the Attorney General (legal.gov.ki), which was unreachable this session (DNS failure / SSL error). No VAT-Act-specific title/year was found under either the V or G PacLII index letters -- this iteration does not include a separate VAT statute entry to paper over that gap."
     :statute/url "https://web.archive.org/web/20241217151226/https://www.paclii.org/ki/legis/consol_act/toc-I.html"
     :statute/url-provenance :archived-paclii-org
     :statute/enacted-date nil
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-kir statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "KIR")) " KIR statute(s) seeded with an "
                 "official citation (the Income Tax Ordinance entry is "
                 "title-only, an honest gap disclosed in the namespace "
                 "docstring, not an omission by design). Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :data-protection)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
