import { useTranslation } from 'react-i18next'
import english from '../images/ic_flag_en.svg'
import german from '../images/ic_flag_de.svg'
// ----------------------------------------------------------------------

const CustomLanguages = [
  {
    value: 'en',
    label: 'English',
    //icon: english
  },
  {
    value: 'hi',
    label: 'Hindi',
    //icon: german
  }
]

// ----------------------------------------------------------------------

export default function useLocales() {
  const { i18n, t: translate } = useTranslation()
  const langStorage = localStorage.getItem('i18nextLng')
  const currentLang =
    CustomLanguages.find(_lang => _lang.value === langStorage) ||
    CustomLanguages[0]
  const handleChangeLanguage = (newLang) => {
    i18n.changeLanguage(newLang)
  }

  return {
    onChangeLang: handleChangeLanguage,
    translate,
    currentLang,
    allLang: CustomLanguages
  }
}
