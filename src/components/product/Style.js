import { styled } from '@mui/material/styles';

export const InputStyle = styled('input')(({ theme }) => ({
    border:'none',
    textAlign:'center',
    width: '100px',
    height: '40px',
    display: 'inline-block',
    lineHeight: '26px',
    borderRadius: '4px',
    backgroundColor: theme.palette.grey[200]
}));