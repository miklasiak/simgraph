package gui;

import virtualcamera.IManagement;
import virtualcamera.IMovement;

/**
 *
 * @author alebar
 */
public interface IGui {
    public void reload();
    public int getPanelWidth ();
    public int getPanelHeight ();
    public void setIMovementInterface (IMovement im);
    public void setIManagementInterface (IManagement z);
    public void show();
}
