import { createStyles, makeStyles } from '@mui/styles';

export const useStyles = makeStyles((theme)=>createStyles({
    rightSection: {
        width: '96%',
    },
    inRightSection: {
        padding: '25px 70px 20px 32px',
    },
    adminButton: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        color: '#fff',
    },
    addNewGroup: {
        fontSize: '18px',
        color: '#fff',
        border: '1px solid #428BCA',
        borderRadius: '2px',
        backgroundColor: '#428BCA',
        height: '48px',
        marginLeft: '15px',
        width: '205px',
        '&:hover': {
            backgroundColor: '#428BCA',
        },
    },
    inAdminButton: {
        display: 'flex',
    },
    addNewGroupBulk: {
      width: '165px',
    },
    bottomButtonExpert: {
        textAlign: 'right',
    },
    table: {
        width: '100%',
        borderSpacing: '0px 0px',
        border: '1px solid #7070704D',
        '& tr': {
            '&:nth-child(1)': {
                '& th': {
                    textAlign:'center',
                    borderBottom: '1px solid #7070704D',
                    fontSize: '15px',
                    color: '#1e1e1f',
                    fontFamily: 'Gilroy-Semibold',
                    padding: '5px 10px',
                    backgroundColor: '#b0b0b1',
                    '& span': {
                        padding: '0px',
                        color: '#000',
                        '& svg': {
                            verticalAlign: 'top',
                        },
                    },
                },
            },
        },
        '& td': {
            textAlign:'center',
            '&:nth-child(2)': {
                color: '#428BCA',
            },
            '&:last-child': {
                color: '#428BCA',
                cursor: 'pointer',
            },
        },
    },
    selectBoxStyle: {
        '& span': {
            display: 'none',
        },
    },
    selectBoxSectionTarget: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'flex-end',
    },
    selctAutTar: {
        width: '14%',
        marginRight: '7px',
        position: 'relative',
        '& div': {
            width: '100%',
            borderRadius: '2px',
        },
        '& input': {
            padding: '15px 14px',
            fontSize: '15px',
        },
    },
    actionList: {
        display: 'flex',
        justifyContent: 'flex-end',
        flexGrow: '1',
    },
    selctAutTarDate: {
        color:'#fff',
        marginRight: '7px',
        position: 'relative',
        '& input': {
            padding: '14px 0px 12px 10px',
            fontSize: '15px',
        },
    },
    calendarIcon: {
        position: 'absolute',
        right: '40px',
        top: '14px',
        color: '#8F8FB3',
        fontSize: '14px',
        zIndex: '99',
    },
    datePicker: {
        height: '48px',
    },
    textField: {
        color: '#428BCA',
        border: '1px solid #428BCA',
        fontSize: '15px',
        fontFamily: 'Gilroy-Semibold',
        padding: '5px 10px',
        '& input' : {
            color: '#428BCA',
        },
    },
    saleRate:{
        //color:'#4289c7',
        border:'none',
        textAlign:'center',
        width: '100px',
        height: '40px',
        display: 'inline-block',
        lineHeight: '26px',
        borderRadius: '4px',
        backgroundColor: theme.palette.grey[300]
    },
    saleRateActive:{
        //color:'#fff',
        border:'none',
        textAlign:'center',
        width: '100px',
        height: '40px',
        display: 'inline-block',
        lineHeight: '26px',
        borderRadius: '4px',
        backgroundColor: theme.palette.grey[200]
    },
  }));