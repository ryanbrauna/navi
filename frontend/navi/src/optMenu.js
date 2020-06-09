import React from 'react';
import IconButton from '@material-ui/core/IconButton';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import AccountBoxIcon from '@material-ui/icons/AccountBox';

export default function LongMenu() {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const verficarUsuario = () => {
        if (sessionStorage.getItem('@NAVI/tipo') == null) {
            window.location = "/";
        }
    }

    return (
        <div>
            <IconButton
                aria-label="more"
                aria-controls="long-menu"
                aria-haspopup="true"
                onClick={handleClick}
            >
                <MoreVertIcon />
            </IconButton>
            <Menu
                id="long-menu"
                anchorEl={anchorEl}
                keepMounted
                open={open}
                onClose={handleClose}
            >
                <MenuItem className="color-navi" onClick={() => {
                     window.location = "/perfil";
                }}>
                    <AccountBoxIcon className="icon" />
                    <span>Perfil</span>
                </MenuItem>
                <MenuItem className="color-navi" onClick={() => {
                    sessionStorage.removeItem('@NAVI/tipo');
                    sessionStorage.removeItem('@NAVI/nome');
                    sessionStorage.removeItem('@NAVI/cod');
                    sessionStorage.removeItem('@NAVI/loja');
                    verficarUsuario();
                }}>
                    <ExitToAppIcon className="icon" />
                    <span>Sair</span>
                </MenuItem>
            </Menu>
        </div>
    );
}
