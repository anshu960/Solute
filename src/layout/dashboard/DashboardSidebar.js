import * as React from 'react';
import PropTypes from 'prop-types';
import { useEffect,useState,useCallback } from 'react';
import { Link as RouterLink, useLocation, useHistory } from 'react-router-dom';
// material
import { styled } from '@mui/material/styles';
import { Box, Link, Drawer, Typography, Stack, List, ListSubheader, Avatar } from '@mui/material';
// components
import PersonOutlinedIcon from '@mui/icons-material/PersonOutlined';
import NavSection from '../../components/NavSection';
import { MHidden } from '../../components/@material-extend';
//
import sidebarConfig from './SidebarConfig';
import EducationConfig from './EducationConfig';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import HomeMaxRoundedIcon from '@mui/icons-material/HomeMaxRounded';
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import { getBusiness, getBusinessId, getUser, getUserId, setBusiness, setBusinessId } from '../../services/authService';
import { matchPath } from 'react-router-dom';
import SidebarItem from './SidebarItem';
import { PATH_PAGE, PATH_DASHBOARD } from '../../routes/path';
import {useDispatch, useSelector} from 'react-redux'
// ----------------------------------------------------------------------

const DRAWER_WIDTH = 280;

const RootStyle = styled('div')(({ theme }) => ({
  [theme.breakpoints.up('lg')]: {
    flexShrink: 0,
    width: DRAWER_WIDTH
  }
}));

const AccountStyle = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  padding: theme.spacing(2, 2.5),
  borderRadius: theme.shape.borderRadiusSm,
  backgroundColor: theme.palette.grey[200]
}));

// ----------------------------------------------------------------------

DashboardSidebar.propTypes = {
  isOpenSidebar: PropTypes.bool,
  onCloseSidebar: PropTypes.func
};

export default function DashboardSidebar({ isOpenSidebar, onCloseSidebar }) {
  const { pathname } = useLocation();
  const [Business, setBusinessDet] = useState({});
  const [user, setUser] = useState({});
  const businessTypes = useSelector(state => state.businessTypes.businessTypes)
  const [menuConfig, setMenuConfig] = useState(sidebarConfig);
  const history = useHistory();
  useEffect(() => {
    if (isOpenSidebar) {
      onCloseSidebar();
    }
    const petroID = getBusinessId();
    const petro = getBusiness();
    const user = getUser();
    setBusinessDet(petro);
    setUser(user);
    if(!petroID){
      refreshBusiness();
    }
    if(petro && petro.BusinessTypeID && petro.BusinessTypeID === "61c094801934fd6e036b6550"){
      setMenuConfig(EducationConfig)
    }
  }, [pathname]);

  const refreshBusiness=()=>{
    const UserID = getUserId();
    const request = {UserID}
    console.log("request",request)
    SendEvent(SocketEvent.RETRIVE_BUSINESS,request,handleRetriveBusinessEvent)
  }
  const handleRetriveBusinessEvent = useCallback((data) => {
    console.log("handleRetriveBusinessEvent in DashboardSideBar",data)
    if(data && data.Payload && data.Payload._id){
      setBusinessId(data.Payload._id)
      setBusiness(data.Payload);
    }else{
        console.log("Unable to find Business bunk in in DashboardSideBar, please try after some time");
    }
  }, []);
  console.log(Business)
  const getBusinessImageUrl=(id)=>{
    const businessType = businessTypes.filter((bType)=>bType._id === id)
     if(businessType && businessType.length && businessType[0].Image){
      return businessType[0].Image[0];
     }
  }
  const renderContent = (
    // <Scrollbar
    <Box
      sx={{
        height: '100%',
        '& .simplebar-content': { height: '100%', display: 'flex', flexDirection: 'column' }
      }}
    >
      <Box sx={{ px: 2.5, pb: 3, mt: 10 }}>
        <Stack
          alignItems="center"
        >
          <Link underline="none" component={RouterLink} to="#"
          sx={{
            width: '100%',
          }}
          >
          <AccountStyle
            sx={{
              display: 'flex', 
              flexDirection: 'column',
            }}
          >
            <Box
            sx = {{
              display: 'flex',
              flexDirection: 'row',
              justifyContent: 'flex-start',
              width: '100%',
              alignItems: 'center',
            }}
            onClick={()=>{history.push(PATH_DASHBOARD.home)}}
            >
              <KeyboardBackspaceIcon sx={{ width: 20, height: 20 }} />
              <Box
              component="span"
              sx={{
                marginLeft: '5px',
                fontSize: '15px', 
              }}
              >
              {"Home"}
                </Box>
            </Box>
          <Box
            onClick={()=>{history.push(PATH_DASHBOARD.businessProfile)}}
          >
            
              {Business.ProfilePicture && Business.ProfilePicture.length ?
              <Avatar>
              <img
              style={{height: '40px', width: '40px'}}
              alt='Logo'
              src={Business.ProfilePicture[0]}
            />
            </Avatar>
            :
            <Avatar>
            <img
                style={{height: '20px', width: '20px'}}
                alt='Logo'
                src={getBusinessImageUrl(Business.BusinessTypeID)}
              />
              </Avatar>
              }
              
            
            {/* <HomeMaxRoundedIcon sx={{ width: 48, height: 48 }} /> */}
          </Box>
          <Box sx={{ textAlign: 'center' }}>
          <Typography variant="subtitle2" sx={{ color: 'text.primary' }}>
            {Business && Business.Name ? Business.Name : ""}
          </Typography>
          <Typography variant="body2" sx={{ color: 'text.secondary' }}>
            {Business && Business.Address ? Business.Address : ""}
          </Typography>
          </Box>
          </AccountStyle>
          </Link>
        </Stack>
      </Box>

      <Box sx={{ mb: 5, mx: 2.5 }}>
        <Link underline="none" component={RouterLink} to="#">
          <AccountStyle>
            <PersonOutlinedIcon />
            <Box sx={{ ml: 2 }}>
              <Typography variant="subtitle2" sx={{ color: 'text.primary' }}>
              { (user && user.Name) || ""}
              </Typography>
              <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                {"owner"}
              </Typography>
            </Box>
          </AccountStyle>
        </Link>
      </Box>

      {menuConfig.isMultiMenu ? menuConfig.menuConfig.map(list => (
        <List
          disablePadding
          key={list.subheader}
          // subheader={
          //   <ListSubheader
          //     disableSticky
          //     disableGutters
          //     sx={{
          //       mt: 3,
          //       mb: 2,
          //       pl: 5,
          //       color: 'text.primary',
          //       typography: 'overline'
          //     }}
          //   >
          //     {list.subheader}
          //   </ListSubheader>
          // }
        >
          {renderSidebarItems({
            // @ts-ignore
            items: list.items,
            pathname
          })}
        </List>
      )): null}

      { menuConfig.isMultiMenu ? null : <NavSection navConfig={menuConfig.menuConfig} /> }

      <Box sx={{ flexGrow: 1 }} />

      {/* <Box sx={{ px: 2.5, pb: 3, mt: 10 }}>
        <Stack
          alignItems="center"
          spacing={3}
          sx={{
            p: 2.5,
            pt: 5,
            borderRadius: 2,
            position: 'relative',
            bgcolor: 'grey.200'
          }}
        >
          <Box
            component="img"
            src={ImageConfig.Favicon}
            sx={{ width: 100, position: 'absolute', top: -50 }}
          />

          <Box sx={{ textAlign: 'center' }}>
            <Typography gutterBottom variant="h6">
              Get more?
            </Typography>
            <Typography variant="body2" sx={{ color: 'text.secondary' }}>
              Get in touch on click powered by
            </Typography>
          </Box>

          <Button
            fullWidth
            href="https://parmartechnologies.com/"
            target="_blank"
            variant="contained"
          >
            Powered By
          </Button>
        </Stack>
      </Box> */}
    </Box>
  );

  function reduceChild({ array, item, pathname, level }) {
    const key = item.path + level
  
    if (item.items) {
      const match = matchPath(pathname, {
        path: item.path,
        end: false
      })
  
      return [
        ...array,
        <SidebarItem
          key={key}
          level={level}
          icon={item.icon}
          //info={item.info}
          href={item.path}
          title={item.title}
          open={Boolean(match)}
        >
          {renderSidebarItems({
            pathname,
            level: level + 1,
            items: item.items
          })}
        </SidebarItem>
      ]
    }
    return [
      ...array,
      <SidebarItem
        open={false}
        key={key}
        level={level}
        href={item.path}
        icon={item.icon}
        //info={item.info}
        title={item.title}
      />
    ]
  }

  function renderSidebarItems({
    items,
    pathname,
    level = 0
  }) {
    return (
      <List disablePadding>
        {items && items.reduce(
          (array, item) => reduceChild({ array, item, pathname, level }),
          []
        )}
      </List>
    )
  }

  return (
    <RootStyle>
      <MHidden width="lgUp">
        <Drawer
          open={isOpenSidebar}
          onClose={onCloseSidebar}
          PaperProps={{
            sx: { width: DRAWER_WIDTH }
          }}
        >
          {renderContent}
        </Drawer>
      </MHidden>

      <MHidden width="lgDown">
        <Drawer
          open
          variant="persistent"
          PaperProps={{
            sx: {
              width: DRAWER_WIDTH,
              bgcolor: 'background.default'
            }
          }}
        >
          {renderContent}
        </Drawer>
      </MHidden>
    </RootStyle>
  );
}
