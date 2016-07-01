/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.kitalpha.vp.requirements.ui.properties.fields;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.helpers.LockHelper;
import org.polarsys.kitalpha.vp.requirements.Requirements.RequirementsPackage;
import org.polarsys.kitalpha.vp.requirements.ui.properties.Messages;

/**
 * @author Joao Barata
 */
public class BasicReqIFElementGroup extends AbstractSemanticField {

  protected Text nameField;
  protected Text chapternameField;
  protected Text textField;

  /**
   * @param parent
   * @param widgetFactory
   */
  public BasicReqIFElementGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean onlyName) {
    super(widgetFactory);

    Group textGroup = _widgetFactory.createGroup(parent, ICommonConstants.EMPTY_STRING);
    textGroup.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    textGroup.setLayoutData(gd);

    nameField = createTextField(textGroup, Messages.getString("ReqIFElement.NameLabel")); //$NON-NLS-1$
    if (!onlyName) {
      chapternameField = createTextField(textGroup, Messages.getString("ReqIFElement.ChapterNameLabel")); //$NON-NLS-1$
      textField = createTextField(textGroup, Messages.getString("ReqIFElement.TextLabel")); //$NON-NLS-1$
    }
  }

  /**
   * @param textGroup
   * @param textLabel
   */
  private Text createTextField(Group textGroup, String textLabel) {
    _widgetFactory.createCLabel(textGroup, textLabel);

    Text field = _widgetFactory.createText(textGroup, ICommonConstants.EMPTY_STRING);
    field.addFocusListener(this);
    field.addKeyListener(this);
    field.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    return field;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject semanticElement) {
    loadData(semanticElement, null);

    if (null != _semanticElement) {
      if (null != nameField)
        setTextValue(nameField, _semanticElement, RequirementsPackage.eINSTANCE.getReqIFElement_Name());
      if (null != chapternameField)
        setTextValue(chapternameField, _semanticElement, RequirementsPackage.eINSTANCE.getRequirement_ReqIF_ChapterName());
      if (null != textField)
        setTextValue(textField, _semanticElement, RequirementsPackage.eINSTANCE.getRequirement_ReqIF_Text());
    }
  }

  /**
   * @param field text field to be filled
   */
  @Override
  protected void fillTextField(Text field) {
    if (field.equals(nameField)) {
      setDataValue(_semanticElement, RequirementsPackage.eINSTANCE.getReqIFElement_Name(), nameField.getText());
    } else if (field.equals(chapternameField)) {
      setDataValue(_semanticElement, RequirementsPackage.eINSTANCE.getRequirement_ReqIF_ChapterName(), chapternameField.getText());
    } else if (field.equals(textField)) {
      setDataValue(_semanticElement, RequirementsPackage.eINSTANCE.getRequirement_ReqIF_Text(), textField.getText());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    LockHelper.getInstance().enable(nameField, enabled);
    LockHelper.getInstance().enable(chapternameField, enabled);
    LockHelper.getInstance().enable(textField, enabled);
  }
}
